package com.quinnox.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.Device;
import com.quinnox.model.DeviceSuggestions;
import com.quinnox.service.DeviceService;

@CrossOrigin
@RestController
@RequestMapping(value = "/inventory/v1")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@Value("${current.app.url}")
	private String currentAppURL;

	@GetMapping("/getAlldevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Device> getAlldevices() {
		return deviceService.getAll();
	}

	@GetMapping("/getLiveDevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Device> getLiveDevices() {
		return deviceService.getLiveDevices();
	}

	@PostMapping("/addDevice")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Device addDevice(@RequestBody Device device) {
		return deviceService.addDevice(device);
	}

	@PostMapping("/addMultipleDevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Device> addDevices(@RequestBody List<Device> devices) {
		return deviceService.addDevices(devices);
	}

	@PutMapping("/updateDevice")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Device updateDevice(@RequestBody Device device) throws Exception {
		return deviceService.addDevice(device);
	}

	@GetMapping("/getDevice/{deviceId}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Device getDevice(@PathVariable String deviceId) throws Exception {
		return deviceService.getDevice(deviceId);
	}

	@DeleteMapping("/deleteDevice/{deviceId}")
	@RolesAllowed("ROLE_ADMIN")
	public void deleteDevice(@PathVariable String deviceId) throws Exception {
		deviceService.deleteDevice(deviceId);
	}

	@GetMapping("/getDeviceList")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<DeviceSuggestions> getDeviceList() {
		return deviceService.getDeviceBrandList();
	}

	@PostMapping("/refresh/deviceSuggestions")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public String addDevices() {
		return deviceService.refreshBrandDevicesListInDB();
	}

	@GetMapping("/uniqueURL/generate")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public String genDeviceCaptureAPI() {
		JSONObject json = new JSONObject();
		String uniqueId = UUID.randomUUID().toString();
		json.put("uniqueId", uniqueId);
		json.put("url", currentAppURL + uniqueId);
		return json.toString();

	}

	@GetMapping("/getAllDevicesCount")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public org.json.simple.JSONObject getAllDevicesCount() throws Exception {
		return deviceService.getAllDevicesCount();
	}

	@GetMapping("/getAllDevicesCountByState")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public org.json.simple.JSONObject getAllDevicesCountByState() throws Exception {
		return deviceService.getAllDevicesCountByState();
	}

	@GetMapping("/getAllDevicesCountByOS")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public org.json.simple.JSONObject getAllDevicesCountByOS() throws Exception {
		return deviceService.getAllDevicesCountByOS();
	}

//	@GetMapping("/getAllDevicesCountByDate")
//	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
//	public org.json.simple.JSONObject getAllDevicesCountByDate() throws Exception {
//		return deviceService.getAllDevicesCountByDate();
//	}

	@GetMapping("/getAllDevicesCountByMakerAndModel")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public org.json.simple.JSONObject getAllDevicesCountByMakerAndModel() throws Exception {
		return deviceService.getAllDevicesCountByMakerAndModel();
	}

}
