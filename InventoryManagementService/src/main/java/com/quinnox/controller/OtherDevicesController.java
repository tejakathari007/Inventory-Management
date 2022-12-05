package com.quinnox.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.OtherDevices;
import com.quinnox.service.OtherDevicesService;

@CrossOrigin
@RestController
@RequestMapping(value = "/inventory/v1")
public class OtherDevicesController {

	@Autowired
	private OtherDevicesService otherDevicesService;

	@GetMapping("/getAllOtherDevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<OtherDevices> getAllOtherDevices() {
		return otherDevicesService.getAll();
	}

	@PostMapping("/addOtherDevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public OtherDevices addOtherDevices(@RequestBody OtherDevices otherDevices) throws Exception {
		return otherDevicesService.addOtherDevices(otherDevices);
	}

	@PostMapping("/addMultipleOtherDevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<OtherDevices> addMultipleOtherDevices(@RequestBody List<OtherDevices> otherDevices) {
		return otherDevicesService.addMultipleOtherDevices(otherDevices);
	}

	@PutMapping("/updateOtherDevices")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public OtherDevices updateOtherDevices(@RequestBody OtherDevices otherDevices) throws Exception {
		return otherDevicesService.addOtherDevices(otherDevices);
	}

	@GetMapping("/getOtherDevices/{oDeviceId}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public OtherDevices getOtherDevices(@PathVariable String oDeviceId) throws Exception {
		return otherDevicesService.getOtherDevices(oDeviceId);
	}

	@DeleteMapping("/deleteOtherDevices/{oDeviceId}")
	@RolesAllowed("ROLE_ADMIN")
	public void deleteOtherDevices(@PathVariable String oDeviceId) throws Exception {
		otherDevicesService.deleteOtherDevicesByID(oDeviceId);
	}
	
	@GetMapping("/getAllOtherDevicesCountByType")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllOtherDevicesCountByType() throws Exception {
		return otherDevicesService.getAllOtherDevicesCountByType();
	}

}
