package com.quinnox.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.quinnox.model.BrowserStatus;
import com.quinnox.model.DeviceOS;
import com.quinnox.model.DeviceState;
import com.quinnox.model.DeviceStatus;
import com.quinnox.model.DeviceType;
import com.quinnox.model.HostingType;
import com.quinnox.model.InstanceType;
import com.quinnox.model.MaintainanceType;
import com.quinnox.model.Region;
import com.quinnox.model.ServerOS;
import com.quinnox.model.State;

@Service
public class ConstantService {

	@SuppressWarnings("unchecked")
	public JSONObject getAllConstants() {
		JSONObject json = new JSONObject();
		JSONObject devJson = new JSONObject();
		List<String> devType = Stream.of(DeviceType.values()).map(DeviceType::name).collect(Collectors.toList());
		List<String> devOS = Stream.of(DeviceOS.values()).map(DeviceOS::name).collect(Collectors.toList());
		List<String> devState = Stream.of(DeviceState.values()).map(DeviceState::name).collect(Collectors.toList());
		List<String> devMaintainanceType = Stream.of(MaintainanceType.values()).map(MaintainanceType::name)
				.collect(Collectors.toList());
		List<String> devStatus = Stream.of(DeviceStatus.values()).map(DeviceStatus::name).collect(Collectors.toList());

		JSONObject serverJson = new JSONObject();
		List<String> serverInstanceType = Stream.of(InstanceType.values()).map(InstanceType::name)
				.collect(Collectors.toList());
		List<String> serverState = Stream.of(State.values()).map(State::name).collect(Collectors.toList());
		List<String> serverMaintainanceType = Stream.of(MaintainanceType.values()).map(MaintainanceType::name)
				.collect(Collectors.toList());
		List<String> serverOS = Stream.of(ServerOS.values()).map(ServerOS::name).collect(Collectors.toList());
		List<String> serverRegion = Stream.of(Region.values()).map(Region::name).collect(Collectors.toList());
		List<String> serverHostingType = Stream.of(HostingType.values()).map(HostingType::name)
				.collect(Collectors.toList());
		
		JSONObject browserJson = new JSONObject();
		List<String> browserStatus = Stream.of(BrowserStatus.values()).map(BrowserStatus::name)
				.collect(Collectors.toList());
		browserJson.put("Browser", browserStatus);

		
		devJson.put("DeviceType", devType);
		devJson.put("DeviceOS", devOS);
		devJson.put("DeviceState", devState);
		devJson.put("MaintainanceType", devMaintainanceType);
		devJson.put("DeviceStatus", devStatus);
		serverJson.put("InstanceType", serverInstanceType);
		serverJson.put("State", serverState);
		serverJson.put("MaintainanceType", serverMaintainanceType);
		serverJson.put("ServerOS", serverOS);
		serverJson.put("Region", serverRegion);
		serverJson.put("HostingType", serverHostingType);

		json.put("Device", devJson);
		json.put("Server", serverJson);
		json.put("Browser", browserJson);
		
		return json;
	}
}
