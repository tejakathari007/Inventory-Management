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

import com.quinnox.model.OtherDevices;
import com.quinnox.repository.OtherDevicesRepository;

@Service
public class OtherDevicesService {

	@Autowired
	private OtherDevicesRepository otherDevicesRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoOperations mongoOperations;

	public List<OtherDevices> getAll() {
		return otherDevicesRepository.findAll();
	}

	public OtherDevices addOtherDevices(OtherDevices otherDevices) {
		return otherDevicesRepository.save(otherDevices);
	}

	public List<OtherDevices> addMultipleOtherDevices(List<OtherDevices> otherDevices) {
		return otherDevicesRepository.saveAll(otherDevices);
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

	public OtherDevices getOtherDevices(String oDeviceId) throws Exception {
		Optional<OtherDevices> oDevId = otherDevicesRepository.findById(oDeviceId);
		if (oDevId.isPresent())
			return oDevId.get();
		else
			throw new Exception("Can't find the OtherDevices");
	}

	public void deleteOtherDevicesByID(String oDeviceId) throws Exception {
		otherDevicesRepository.deleteById(oDeviceId);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllOtherDevicesCountByName() throws Exception {
		JSONObject json = new JSONObject();

		Query oDevicesHub = new Query();
		oDevicesHub.addCriteria(Criteria.where("name").is("Hub"));
		int overallHub = (int) mongoOperations.count(oDevicesHub, OtherDevices.class);

		Query oDevicesRouter = new Query();
		oDevicesRouter.addCriteria(Criteria.where("name").is("Router"));
		int overallRouter = (int) mongoOperations.count(oDevicesRouter, OtherDevices.class);

		Query oDevicesSwitches = new Query();
		oDevicesSwitches.addCriteria(Criteria.where("name").is("Switches"));
		int overallSwitches = (int) mongoOperations.count(oDevicesSwitches, OtherDevices.class);

		JSONObject overallOtherDevicesByName = new JSONObject();
		overallOtherDevicesByName.put("Hub", overallHub);
		overallOtherDevicesByName.put("Router", overallRouter);
		overallOtherDevicesByName.put("Switches", overallSwitches);
		json.put("Other Devices Count By Name", overallOtherDevicesByName);
		return json;
	}

}
