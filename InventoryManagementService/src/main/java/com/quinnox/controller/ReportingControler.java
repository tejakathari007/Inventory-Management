package com.quinnox.controller;

import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.TreeChartModel;
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
	@GetMapping("/getReport")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Map<String,TreeChartModel> getTreeChart() {
		return reportingService.getTreeChart();
	}

}
