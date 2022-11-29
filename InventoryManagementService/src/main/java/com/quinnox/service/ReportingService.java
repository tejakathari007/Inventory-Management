package com.quinnox.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {

	@Autowired
	private ServerService serverService;

	@Autowired
	private ClientService clientService;

	public String getAll() {

		JSONObject json = new JSONObject();
		json.put("servers", serverService.getAll());
		json.put("clients", clientService.getAll());

		return json.toString();
	}

}
