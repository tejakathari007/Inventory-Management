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

import com.quinnox.model.Server;
import com.quinnox.service.ServerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/inventory/v1")
public class ServerController {

	@Autowired
	private ServerService serverService;

	@GetMapping("/getAllServers")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Server> getAllServers() {
		return serverService.getAll();
	}

	@PostMapping("/addServer")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Server addServer(@RequestBody Server server) throws Exception {
		return serverService.addServer(server);
	}

	@PostMapping("/addMultipleServers")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Server> addServers(@RequestBody List<Server> servers) {
		return serverService.addServers(servers);
	}

	@PutMapping("/updateServer")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Server updateServer(@RequestBody Server server) throws Exception {
		return serverService.updateServer(server);
	}

	@GetMapping("/getServer/{serverId}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Server getServer(@PathVariable String serverId) throws Exception {
		return serverService.getServer(serverId);
	}

	@DeleteMapping("/deleteServer/{serverId}")
	@RolesAllowed("ROLE_ADMIN")
	public void deleteServer(@PathVariable String serverId) throws Exception {
		serverService.deleteServerByID(serverId);
	}
	
	@GetMapping("/getAllServerCountPerRegion")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllServerCountPerRegion() throws Exception {
		return serverService.getAllServerCountPerRegion();
	}

	@GetMapping("/getAllServerCountByState")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllServerCountByState() throws Exception {
		return serverService.getAllServerCountByState();
	}

	@GetMapping("/getAllServerCountByHostingType")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllServerCountByHostingType() throws Exception {
		return serverService.getAllServerCountByHostingType();
	}

	@GetMapping("/getAllServerCountByOS")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllServerCountByOS() throws Exception {
		return serverService.getAllServerCountByOS();
	}
	
	@GetMapping("/getAllServersCount")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllServersCount() throws Exception {
		return serverService.getAllServersCount();
	}

	@GetMapping("/getAllServersCountByDate")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllServersCountByDate() throws Exception {
		return serverService.getAllServersCountByDate();
	}

	@GetMapping("/getAvgDeviceCountPerServer")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAvgDeviceCountPerServer() throws Exception {
		return serverService.getAvgDeviceCountPerServer();
	}

	@GetMapping("/getAvgBrowserCountPerServer")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAvgBrowserCountPerServer() throws Exception {
		return serverService.getAvgBrowserCountPerServer();
	}

}
