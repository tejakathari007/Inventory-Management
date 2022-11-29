package com.quinnox.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.quinnox.model.Browser;
import com.quinnox.model.BrowserStatus;
import com.quinnox.model.Client;
import com.quinnox.model.Device;
import com.quinnox.model.DeviceState;
import com.quinnox.model.Server;
import com.quinnox.repository.BrowserRepository;
import com.quinnox.repository.ClientRepository;
import com.quinnox.repository.ServerRepository;

@Service
public class BrowserService {

	@Autowired
	private BrowserRepository browserRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ServerRepository serverRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoOperations mongoOperations;

	public List<Browser> getAll() {
		return browserRepository.findAll();
	}

	public List<Browser> getAvailBrowsers() {
		return browserRepository.findByBrowserStatus(BrowserStatus.AVAILABLE.toString());
	}

	public Browser addBrowser(Browser browser) {

		if (browser.getId() == null || browser.getClient() == null) {
			browser.setBrowserStatus(BrowserStatus.AVAILABLE);
		}

		if (browser.getClient() != null) {
			Optional<Client> client = clientRepository.findById(browser.getClient().getId());
			if (client.isPresent())
				browser.setClient(client.get());
			browser.setBrowserStatus(BrowserStatus.OCCUPIED);

		}
		if (browser.getServer() != null) {
			Optional<Server> server = serverRepository.findById(browser.getServer().getId());
			if (server.isPresent())
				browser.setServer(server.get());
		}
		return browserRepository.save(browser);
	}

	public List<Browser> addBrowsers(List<Browser> browsers) {
		return browserRepository.saveAll(browsers);
	}

//	public Server updateServer(Server server, String serverId) throws Exception {
//		Optional<Server> ser = browserRepository.findById(serverId);
//		if (!ser.isPresent()) {
//			throw new Exception("Server not found");
//		} else {
//			Server svr = ser.get();
//			svr = browserRepository.save(server);
//			return svr;
//		}
//
//	}

	public Browser getBrowser(String browserId) throws Exception {
		Optional<Browser> browser = browserRepository.findById(browserId);
		if (browser.isPresent())
			return browser.get();
		else
			throw new Exception("Can't find the browser");
	}

	public void deleteBrowser(String browserId) throws Exception {
		browserRepository.deleteById(browserId);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllBrowsersCount() throws Exception {

		JSONObject json = new JSONObject();

		Query query = new Query();
		int browserCount = (int) mongoTemplate.count(query, Browser.class);
		json.put("Overall Browser Count", browserCount);

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllBrowsersCountByStatus() throws Exception {
		JSONObject json = new JSONObject();

		Query browserAv = new Query();
		browserAv.addCriteria(Criteria.where("browserStatus").is(BrowserStatus.AVAILABLE));
		int overallAv = (int) mongoOperations.count(browserAv, Browser.class);

		Query browserOcc = new Query();
		browserOcc.addCriteria(Criteria.where("browserStatus").is(BrowserStatus.OCCUPIED));
		int overallOcc = (int) mongoOperations.count(browserOcc, Browser.class);

		Query browserNA = new Query();
		browserNA.addCriteria(Criteria.where("browserStatus").is(BrowserStatus.NA));
		int overallNA = (int) mongoOperations.count(browserNA, Browser.class);

		JSONObject overallBrowserByState = new JSONObject();
		overallBrowserByState.put("Available", overallAv);
		overallBrowserByState.put("Occupied", overallOcc);
		overallBrowserByState.put("NA", overallNA);
		json.put("Browser Count By Status", overallBrowserByState);
		return json;
	}

}
