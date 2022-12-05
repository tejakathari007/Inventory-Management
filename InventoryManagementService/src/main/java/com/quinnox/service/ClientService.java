package com.quinnox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.quinnox.model.Browser;
import com.quinnox.model.BrowserStatus;
import com.quinnox.model.ChartModel;
import com.quinnox.model.Client;
import com.quinnox.model.ClientBrowser;
import com.quinnox.model.ClientDevice;
import com.quinnox.model.Device;
import com.quinnox.model.DeviceState;
import com.quinnox.model.DeviceStatus;
import com.quinnox.repository.BrowserRepository;
import com.quinnox.repository.ClientRepository;
import com.quinnox.repository.DeviceRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private BrowserRepository browserRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoOperations mongoOperations;

	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	public Client updateClient(Client client) throws Exception {
		List<ClientDevice> cDevice = client.getDevices();
		List<ClientBrowser> cBrowser = client.getBrowsers();

		Client cli = getClient(client.getId());
		cli.getDevices().forEach((each) -> {
			if (!cDevice.contains(each)) {
				Optional<Device> devId = deviceRepository.findById(each.getId());
				devId.get().setClient(null);
				devId.get().setStatus(DeviceStatus.AVAILABLE);
				devId.get().setState(DeviceState.LIVE);
				deviceRepository.save(devId.get());
			}
		});

		cDevice.forEach((each) -> {
			if (!cli.getDevices().contains(each)) {
				Optional<Device> devId = deviceRepository.findById(each.getId());
				if (devId.isPresent()) {
					devId.get().setClient(client);
					devId.get().setState(DeviceState.LIVE);
					devId.get().setStatus(DeviceStatus.OCCUPIED);
					deviceRepository.save(devId.get());
				}
			}
		});

		cli.getBrowsers().forEach((each) -> {
			if (!cBrowser.contains(each)) {

				Optional<Browser> brwId = browserRepository.findById(each.getId());
				brwId.get().setClient(null);
				brwId.get().setBrowserStatus(BrowserStatus.AVAILABLE);
				browserRepository.save(brwId.get());
			}
		});

		cBrowser.forEach((each) -> {
			if (!cli.getBrowsers().contains(each)) {
				Optional<Browser> brwId = browserRepository.findById(each.getId());
				if (brwId.isPresent()) {
					brwId.get().setClient(client);
					brwId.get().setBrowserStatus(BrowserStatus.OCCUPIED);
					browserRepository.save(brwId.get());
				}
			}
		});

		return clientRepository.save(client);
	}

	public Client addClient(Client client) throws Exception {
		List<ClientDevice> sDevice = client.getDevices();
		List<ClientBrowser> sBrowser = client.getBrowsers();
		client.setDevices(new ArrayList<>());
		client.setBrowsers(new ArrayList<>());
		client = clientRepository.save(client);
		for (ClientDevice dev : sDevice) {
			Optional<Device> devId = deviceRepository.findById(dev.getId());
			if (devId.isPresent()) {
				devId.get().setClient(client);
				deviceRepository.save(devId.get());
			}
		}
		for (ClientBrowser brw : sBrowser) {
			Optional<Browser> brwId = browserRepository.findById(brw.getId());
			if (brwId.isPresent()) {
				brwId.get().setClient(client);
				browserRepository.save(brwId.get());
			}
		}
		return getClient(client.getId());
	}

	public List<Client> addClients(List<Client> clients) {
		return clientRepository.saveAll(clients);
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

	public Client getClient(String clientId) throws Exception {
		Optional<Client> clnId = clientRepository.findById(clientId);
		if (clnId.isPresent()) {
			Client client = clnId.get();
			List<Device> device = deviceRepository.findByClient(client.getId());
			List<ClientDevice> clientDevice = new ArrayList<>();
			List<Browser> browser = browserRepository.findByClient(client.getId());
			List<ClientBrowser> clientBrowser = new ArrayList<>();
			device.forEach(dev -> {
				ClientDevice cDevice = modelMapper.map(dev, ClientDevice.class);
				clientDevice.add(cDevice);
			});
			browser.forEach(brw -> {
				ClientBrowser cBrowser = modelMapper.map(brw, ClientBrowser.class);
				clientBrowser.add(cBrowser);
			});
			client.setDevices(clientDevice);
			client.setBrowsers(clientBrowser);
			return client;
		}

		else
			throw new Exception("Can't find the client");
	}

	public List<Client> getAllClient() throws Exception {
		List<Client> clnId = clientRepository.findAll();
		clnId.forEach((each) -> {
			List<Device> device = deviceRepository.findByClient(each.getId());
			List<ClientDevice> clientDevice = new ArrayList<>();
			List<Browser> browser = browserRepository.findByClient(each.getId());
			List<ClientBrowser> clientBrowser = new ArrayList<>();
			device.forEach(dev -> {
				ClientDevice cDevice = modelMapper.map(dev, ClientDevice.class);
				clientDevice.add(cDevice);
			});
			browser.forEach(brw -> {
				ClientBrowser cBrowser = modelMapper.map(brw, ClientBrowser.class);
				clientBrowser.add(cBrowser);
			});
			each.setDevices(clientDevice);
			each.setBrowsers(clientBrowser);
		});

		return clnId;
	}

	public void deleteClient(String clientId) throws Exception {
		clientRepository.deleteById(clientId);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllClientsCount() throws Exception {

		JSONObject json = new JSONObject();
		Query query = new Query();

		int clientCount = (int) mongoTemplate.count(query, Client.class);
		json.put("ClientCount", clientCount);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getBrowserCountByClient() throws Exception {

//		Map<String, Integer> map = new HashMap<>();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();

		getAllClient().forEach((each) -> {
			ChartModel proc = new ChartModel(each.getName(), each.getBrowsers().size());
			array.add(proc);
		});

		json.put("data", array);

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDeviceCountByClient() throws Exception {

//		Map<String, Integer> map = new HashMap<>();
		JSONObject json = new JSONObject();
		List<Client> s = clientRepository.findAll();
		JSONArray array = new JSONArray();

		getAllClient().forEach((each) -> {
			ChartModel proc = new ChartModel(each.getName(), each.getDevices().size());
			array.add(proc);
		});

		json.put("data", array);

		return json;
	}
}
