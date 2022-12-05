package com.quinnox.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.quinnox.model.ChartModel;
import com.quinnox.model.Client;
import com.quinnox.model.Device;
import com.quinnox.model.DeviceList;
import com.quinnox.model.DeviceOS;
import com.quinnox.model.DeviceState;
import com.quinnox.model.DeviceSuggestions;
import com.quinnox.model.Server;
import com.quinnox.repository.ClientRepository;
import com.quinnox.repository.DeviceRepository;
import com.quinnox.repository.DeviceSuggestionsRepository;
import com.quinnox.repository.ServerRepository;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private DeviceSuggestionsRepository deviceSuggestionsRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ServerRepository serverRepository;

	@Value("${gsmArena.api}")
	private String gsmArenaUrl;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoOperations mongoOperations;

	public List<Device> getAll() {
		return deviceRepository.findAll();
	}

	public List<Device> getLiveDevices() {
		return deviceRepository.findByStateAndStatus("LIVE", "AVAILABLE");
	}

	public Device addDevice(Device device) {

		if (device.getClient() != null) {
			Optional<Client> client = clientRepository.findById(device.getClient().getId());
			if (client.isPresent())
				device.setClient(client.get());
		}
		if (device.getServer() != null) {
			Optional<Server> server = serverRepository.findById(device.getServer().getId());
			if (server.isPresent())
				device.setServer(server.get());
		}

		return deviceRepository.save(device);
	}

	public List<Device> addDevices(List<Device> devices) {
		return deviceRepository.saveAll(devices);
	}

	public Device getDevice(String deviceId) throws Exception {
		Optional<Device> device = deviceRepository.findById(deviceId);
		if (device.isPresent())
			return device.get();
		else
			throw new Exception("Can't find the device");
	}

	public void deleteDevice(String deviceId) throws Exception {
		deviceRepository.deleteById(deviceId);
	}

	private DeviceList getDeviceList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		Map<String, String> vars = new HashMap<>();
		vars.put("route", "device-list");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(gsmArenaUrl).queryParam("route",
				"device-list");
		String uriBuilder = builder.build().encode().toUriString();
		ResponseEntity<DeviceList> deviceList = restTemplate.exchange(uriBuilder, HttpMethod.GET, entity,
				DeviceList.class, vars);
		return deviceList.getBody();
	}

	public String refreshBrandDevicesListInDB() {

		deviceSuggestionsRepository.deleteAll();
		DeviceList list = getDeviceList();
		list.getData().forEach((each) -> {
			String brand = each.getBrandName();
			List<String> devices = new ArrayList<>();
			each.getDeviceList().forEach((device) -> {
				devices.add(device.getDeviceName());
			});
			DeviceSuggestions suggestions = new DeviceSuggestions(brand, devices);
			deviceSuggestionsRepository.save(suggestions);

		});

		return "Refreshed Successfully";

	}

	public List<DeviceSuggestions> getDeviceBrandList() {

		return deviceSuggestionsRepository.findAll();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllDevicesCount() throws Exception {

		JSONObject json = new JSONObject();
		Query query = new Query();

		int deviceCount = (int) mongoTemplate.count(query, Device.class);
		json.put("DeviceCount", deviceCount);
		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllDevicesCountByState() throws Exception {
		JSONObject json = new JSONObject();

		Query deviceProc = new Query();
		deviceProc.addCriteria(Criteria.where("state").is(DeviceState.PROCUREMENT));
		int overallProc = (int) mongoOperations.count(deviceProc, Device.class);

		Query deviceDelv = new Query();
		deviceDelv.addCriteria(Criteria.where("state").is(DeviceState.DELIVERED));
		int overallDelv = (int) mongoOperations.count(deviceDelv, Device.class);

		Query devicePrep = new Query();
		devicePrep.addCriteria(Criteria.where("state").is(DeviceState.PREP));
		int overallPrep = (int) mongoOperations.count(devicePrep, Device.class);

		Query deviceLive = new Query();
		deviceLive.addCriteria(Criteria.where("state").is(DeviceState.LIVE));
		int overallLive = (int) mongoOperations.count(deviceLive, Device.class);

		Query deviceMntn = new Query();
		deviceMntn.addCriteria(Criteria.where("state").is(DeviceState.MAINTAINANCE));
		int overallMntn = (int) mongoOperations.count(deviceMntn, Device.class);

		Query devicePhOut = new Query();
		devicePhOut.addCriteria(Criteria.where("state").is(DeviceState.PHASEDOUT));
		int overallPhOut = (int) mongoOperations.count(devicePhOut, Device.class);

		JSONArray array = new JSONArray();
		ChartModel proc = new ChartModel("Procurement", overallProc);
		ChartModel del = new ChartModel("Delivered", overallDelv);
		ChartModel pr = new ChartModel("Preparation", overallPrep);
		ChartModel li = new ChartModel("Live", overallLive);
		ChartModel mai = new ChartModel("Maintenance", overallMntn);
		ChartModel ph = new ChartModel("PhasedOut", overallPhOut);
		array.add(proc);
		array.add(del);
		array.add(pr);
		array.add(li);
		array.add(mai);
		array.add(ph);

		json.put("data", array);

		return json;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getAllDevicesCountByMakerAndModel() {
		Map<String, Integer> map = new HashMap<>();
		JSONObject json = new JSONObject();
		List<Device> s = deviceRepository.findAll();
		s.forEach((each) -> {
			String makerModel = each.getMaker() + each.getModel();
			if (map.containsKey(makerModel)) {
				map.put(makerModel, map.get(makerModel) + 1);
			} else {
				map.put(makerModel, 1);
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

	@SuppressWarnings("unchecked")
	public JSONObject getAllDevicesCountByOS() throws Exception {
		JSONObject json = new JSONObject();

		Query deviceAndroid = new Query();
		deviceAndroid.addCriteria(Criteria.where("os").is(DeviceOS.ANDROID));
		int overallAndroid = (int) mongoOperations.count(deviceAndroid, Device.class);

		Query deviceIOS = new Query();
		deviceIOS.addCriteria(Criteria.where("os").is(DeviceOS.IOS));
		int overallIOS = (int) mongoOperations.count(deviceIOS, Device.class);

		JSONArray array = new JSONArray();
		ChartModel proc = new ChartModel("Android", overallAndroid);
		ChartModel del = new ChartModel("IOS", overallIOS);
		array.add(proc);
		array.add(del);
		json.put("data", array);
		return json;
	}

//	@SuppressWarnings("unchecked")
//	public JSONObject getAllDevicesCountByDate() {
//		Map<Date, Integer> map = new HashMap<>();
//		JSONObject json = new JSONObject();
//		List<Device> s = deviceRepository.findAll();
//		s.forEach((each) -> {
//			Date createdDate = each.getCreatedDate();
//			createdDate.setHours(0);
//			createdDate.setMinutes(0);
//			createdDate.setSeconds(0);
//			if (map.containsKey(createdDate)) {
//				map.put(createdDate, map.get(createdDate) + 1);
//			} else {
//				map.put(createdDate, 1);
//			}
//
//			json.put("Device Count By createdDate", map);
//
//		});
//		return json;
//	}

}
