package com.quinnox.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.quinnox.model.Browser;
import com.quinnox.model.ChartModel;
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
	public JSONObject getAllOtherDevicesCountByType() throws Exception {

		Map<String, Integer> map = new HashMap<>();
		JSONObject json = new JSONObject();
		List<OtherDevices> s = otherDevicesRepository.findAll();
		s.forEach((each) -> {
			String type = each.getType();
			if (map.containsKey(type)) {
				map.put(type, map.get(type) + 1);
			} else {
				map.put(type, 1);
			}
		});
		JSONArray array = new JSONArray();

		map.forEach((k, v) -> {
			ChartModel proc = new ChartModel(k, v);
			array.add(proc);
		});
		json.put("data", array);

		return json;

	}

}
