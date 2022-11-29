package com.quinnox.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.quinnox.model.Browser;
import com.quinnox.model.BrowserStatus;
import com.quinnox.model.Device;
import com.quinnox.model.DeviceState;
import com.quinnox.model.DeviceStatus;
import com.quinnox.model.HostingType;
import com.quinnox.model.Region;
import com.quinnox.model.Server;
import com.quinnox.model.ServerBrowser;
import com.quinnox.model.ServerDevice;
import com.quinnox.model.ServerOS;
import com.quinnox.model.State;
import com.quinnox.repository.BrowserRepository;
import com.quinnox.repository.DeviceRepository;
import com.quinnox.repository.ServerRepository;

@Service
public class ServerService {

	@Autowired
	private ServerRepository serverRepository;

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

	public List<Server> getAll() {
		return serverRepository.findAll();
	}

	public Server updateServer(Server server) throws Exception {
		List<ServerDevice> sDevice = server.getDevices();
		List<ServerBrowser> sBrowser = server.getBrowsers();

		Server ser = getServer(server.getId());
		ser.getDevices().forEach((each) -> {
			if (!sDevice.contains(each)) {
				Optional<Device> devId = deviceRepository.findById(each.getId());
				devId.get().setServer(null);
				devId.get().setStatus(DeviceStatus.AVAILABLE);
				devId.get().setState(DeviceState.LIVE);
				deviceRepository.save(devId.get());
			}
		});

		sDevice.forEach((each) -> {
			if (!ser.getDevices().contains(each)) {
				Optional<Device> devId = deviceRepository.findById(each.getId());
				if (devId.isPresent()) {
					devId.get().setServer(server);
					devId.get().setState(DeviceState.LIVE);
					devId.get().setStatus(DeviceStatus.OCCUPIED);
					deviceRepository.save(devId.get());
				}
			}
		});

		ser.getBrowsers().forEach((each) -> {
			if (!sBrowser.contains(each)) {

				Optional<Browser> brwId = browserRepository.findById(each.getId());
				brwId.get().setServer(null);
				brwId.get().setBrowserStatus(BrowserStatus.AVAILABLE);
				browserRepository.save(brwId.get());
			}
		});

		sBrowser.forEach((each) -> {
			if (!ser.getBrowsers().contains(each)) {
				Optional<Browser> brwId = browserRepository.findById(each.getId());
				if (brwId.isPresent()) {
					brwId.get().setServer(server);
					brwId.get().setBrowserStatus(BrowserStatus.OCCUPIED);
					browserRepository.save(brwId.get());
				}
			}
		});

		return serverRepository.save(server);
	}

	public Server addServer(Server server) throws Exception {
		List<ServerDevice> sDevice = server.getDevices();
		List<ServerBrowser> sBrowser = server.getBrowsers();
		server.setDevices(new ArrayList<>());
		server.setBrowsers(new ArrayList<>());
		server = serverRepository.save(server);
		for (ServerDevice dev : sDevice) {
			Optional<Device> devId = deviceRepository.findById(dev.getId());
			if (devId.isPresent()) {
				devId.get().setServer(server);
				devId.get().setState(DeviceState.LIVE);
				devId.get().setStatus(DeviceStatus.OCCUPIED);
				deviceRepository.save(devId.get());
			}
		}
		for (ServerBrowser brw : sBrowser) {
			Optional<Browser> brwId = browserRepository.findById(brw.getId());
			if (brwId.isPresent()) {
				brwId.get().setServer(server);
				brwId.get().setBrowserStatus(BrowserStatus.OCCUPIED);
				browserRepository.save(brwId.get());
			}
		}
		return getServer(server.getId());
	}

	public List<Server> addServers(List<Server> servers) {

		return serverRepository.saveAll(servers);
	}

//	public Server updateServer(Server server, String serverId) throws Exception {
//		Optional<Server> ser = serverRepository.findById(serverId);
//		if (!ser.isPresent()) {
//			throw new Exception("Server not found");
//		} else {
//			Server svr = ser.get();
//			svr = serverRepository.save(server);
//			return svr;
//		}
//
//	}

	public Server getServer(String serverId) throws Exception {
		Optional<Server> serId = serverRepository.findById(serverId);

		if (serId.isPresent()) {
			Server server = serId.get();
			List<Device> device = deviceRepository.findByServer(server.getId());
			List<ServerDevice> serverDevice = new ArrayList<>();
			List<Browser> browser = browserRepository.findByServer(server.getId());
			List<ServerBrowser> serverBrowser = new ArrayList<>();
			device.forEach(dev -> {
				ServerDevice sDevice = modelMapper.map(dev, ServerDevice.class);
				serverDevice.add(sDevice);
			});
			browser.forEach(brw -> {
				ServerBrowser sBrowser = modelMapper.map(brw, ServerBrowser.class);
				serverBrowser.add(sBrowser);
			});
			server.setDevices(serverDevice);
			server.setBrowsers(serverBrowser);
			return server;
		} else
			throw new Exception("Can't find the server");
	}

	public void deleteServerByID(String serverId) throws Exception {
		serverRepository.deleteById(serverId);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllServerCountPerRegion() throws Exception {
		JSONObject json = new JSONObject();

		Query serverBlr = new Query();
		serverBlr.addCriteria(Criteria.where("region").is(Region.IND_BLR));
		int overallBlr = (int) mongoOperations.count(serverBlr, Server.class);

		Query serverChg = new Query();
		serverChg.addCriteria(Criteria.where("region").is(Region.USA_CHI));
		int overallChg = (int) mongoOperations.count(serverChg, Server.class);

		Query serverLon = new Query();
		serverLon.addCriteria(Criteria.where("region").is(Region.UK_LON));
		int overallLon = (int) mongoOperations.count(serverLon, Server.class);

		JSONObject overallServerPerRegion = new JSONObject();
		overallServerPerRegion.put("IND_BLR", overallBlr);
		overallServerPerRegion.put("USA_CHI", overallChg);
		overallServerPerRegion.put("UK_LON", overallLon);
		json.put("Server Count Per Region", overallServerPerRegion);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllServerCountByState() throws Exception {
		JSONObject json = new JSONObject();

		Query serverProc = new Query();
		serverProc.addCriteria(Criteria.where("state").is(State.PROCUREMENT));
		int overallProc = (int) mongoOperations.count(serverProc, Server.class);

		Query serverDelv = new Query();
		serverDelv.addCriteria(Criteria.where("state").is(State.DELIVERED));
		int overallDelv = (int) mongoOperations.count(serverDelv, Server.class);

		Query serverPrep = new Query();
		serverPrep.addCriteria(Criteria.where("state").is(State.PREPARATION));
		int overallPrep = (int) mongoOperations.count(serverPrep, Server.class);

		Query serverLive = new Query();
		serverLive.addCriteria(Criteria.where("state").is(State.LIVE));
		int overallLive = (int) mongoOperations.count(serverLive, Server.class);

		Query serverMntn = new Query();
		serverMntn.addCriteria(Criteria.where("state").is(State.MAINTAINANCE));
		int overallMntn = (int) mongoOperations.count(serverMntn, Server.class);

		Query serverPhOut = new Query();
		serverPhOut.addCriteria(Criteria.where("state").is(State.PHASEDOUT));
		int overallPhOut = (int) mongoOperations.count(serverPhOut, Server.class);

		JSONObject overallServerByState = new JSONObject();
		overallServerByState.put("Procurement", overallProc);
		overallServerByState.put("Delivered", overallDelv);
		overallServerByState.put("Preparation", overallPrep);
		overallServerByState.put("Live", overallLive);
		overallServerByState.put("Maintenance", overallMntn);
		overallServerByState.put("PhasedOut", overallPhOut);
		json.put("Server Count By State", overallServerByState);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllServerCountByHostingType() throws Exception {
		JSONObject json = new JSONObject();

		Query serverShared = new Query();
		serverShared.addCriteria(Criteria.where("hostingType").is(HostingType.SHARED));
		int overallShared = (int) mongoOperations.count(serverShared, Server.class);

		Query serverDedicated = new Query();
		serverDedicated.addCriteria(Criteria.where("hostingType").is(HostingType.DEDICATED));
		int overallDedicated = (int) mongoOperations.count(serverDedicated, Server.class);

		JSONObject overallServerByHostingType = new JSONObject();
		overallServerByHostingType.put("Shared", overallShared);
		overallServerByHostingType.put("Dedicated", overallDedicated);
		json.put("Server Count By Hosting Type", overallServerByHostingType);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllServerCountByOS() throws Exception {
		JSONObject json = new JSONObject();

		Query serverLinux = new Query();
		serverLinux.addCriteria(Criteria.where("OS").is(ServerOS.LINUX));
		int overallLinux = (int) mongoOperations.count(serverLinux, Server.class);

		Query serverMac = new Query();
		serverMac.addCriteria(Criteria.where("OS").is(ServerOS.MAC));
		int overallMac = (int) mongoOperations.count(serverMac, Server.class);

		JSONObject overallServerByOS = new JSONObject();
		overallServerByOS.put("Linux", overallLinux);
		overallServerByOS.put("MAC", overallMac);
		json.put("Server Count By OS", overallServerByOS);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllServersCount() throws Exception {

		JSONObject json = new JSONObject();
		Query query = new Query();

		int serverCount = (int) mongoTemplate.count(query, Server.class);
		json.put("Overall Server Count", serverCount);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAvgDeviceCountPerServer() throws Exception {
		JSONObject json = new JSONObject();

		int deviceCount = 0;
		List<Device> devices = deviceRepository.findAll();
		for (Device dev : devices) {
			if (dev.getServer() != null)
				deviceCount++;
		}
		Query query = new Query();
		int serverCount = (int) mongoTemplate.count(query, Server.class);
		int avg = deviceCount / serverCount;
		json.put("Average Device Count Per Server", avg);

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAvgBrowserCountPerServer() throws Exception {
		JSONObject json = new JSONObject();

		int browserCount = 0;
		List<Browser> browser = browserRepository.findAll();
		for (Browser brw : browser) {
			if (brw.getServer() != null)
				browserCount++;
		}
		Query query = new Query();
		int serverCount = (int) mongoTemplate.count(query, Server.class);
		int avg = browserCount / serverCount;
		json.put("Avg Browser Count Per Server", avg);

		return json;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public JSONObject getAllServersCountByDate() {
		Map<Date, Integer> map = new HashMap<>();
		JSONObject json = new JSONObject();
		List<Server> s = serverRepository.findAll();
		s.forEach((each) -> {
			Date createdDate = each.getCreatedDate();
			createdDate.setHours(0);
			createdDate.setMinutes(0);
			createdDate.setSeconds(0);
			if (map.containsKey(createdDate)) {
				map.put(createdDate, map.get(createdDate) + 1);
			} else {
				map.put(createdDate, 1);
			}

			json.put("Server Count By createdDate", map);

		});
		return json;
	}

}
