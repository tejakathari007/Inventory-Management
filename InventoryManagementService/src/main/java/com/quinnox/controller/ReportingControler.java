package com.quinnox.controller;

import javax.annotation.security.RolesAllowed;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.service.ReportingService;

@CrossOrigin
@RestController
@RequestMapping(value = "/inventory/v1")
public class ReportingControler {

	@Autowired
	private ReportingService reportingService;

	@GetMapping("/getAll")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public String getAlldevices() {
		return reportingService.getAll();
	}

}
