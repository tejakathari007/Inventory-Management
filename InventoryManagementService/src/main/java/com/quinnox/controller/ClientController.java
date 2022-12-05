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

import com.quinnox.model.Client;
import com.quinnox.model.Server;
import com.quinnox.service.ClientService;
import com.quinnox.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/inventory/v1")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/getAllClients")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Client> getAllClients() {
		return clientService.getAll();
	}

	@PostMapping("/addClient")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Client addClient(@RequestBody Client client) throws Exception {
		return clientService.addClient(client);
	}

	@PostMapping("/addMultipleClients")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Client> addClients(@RequestBody List<Client> clients) {
		return clientService.addClients(clients);
	}

	@PutMapping("/updateClient")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Client updateClient(@RequestBody Client client) throws Exception {
		return clientService.updateClient(client);
	}
	
	@GetMapping("/getClient/{clientId}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Client getClient(@PathVariable String clientId) throws Exception{
		return clientService.getClient(clientId);
	}
	
	@DeleteMapping("/deleteClient/{clientId}")
	@RolesAllowed("ROLE_ADMIN")
	public void deleteClient(@PathVariable String clientId) throws Exception{
		clientService.deleteClient(clientId);
	}
	
	@GetMapping("/getAllClientsCount")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllClientsCount() throws Exception {
		return clientService.getAllClientsCount();
	}
	
	@GetMapping("/getBrowserCountByClient")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getBrowserCountByClient() throws Exception {
		return clientService.getBrowserCountByClient();
	}
	
	@GetMapping("/getDeviceCountByClient")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getDeviceCountByClient() throws Exception {
		return clientService.getDeviceCountByClient();
	}
}
