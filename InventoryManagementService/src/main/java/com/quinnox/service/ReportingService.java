package com.quinnox.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.model.Client;
import com.quinnox.model.Server;
import com.quinnox.model.TreeChartModel;

@Service
public class ReportingService {

	@Autowired
	private ServerService serverService;

	@Autowired
	private ClientService clientService;

	public Map<String, TreeChartModel> getTreeChart() {

		Map<String, TreeChartModel> json = new HashMap<>();
		json.put("server-data", getServers());
		json.put("client-data", getClients());

		return json;
	}

	public String getAll() {

		JSONObject json = new JSONObject();
		json.put("servers", serverService.getAll());
		json.put("clients", clientService.getAll());

		return json.toString();
	}

	private TreeChartModel getServers() {
		List<Server> servers = serverService.getAll();
		TreeChartModel tree = new TreeChartModel();
		tree.setName("Servers");
		List<TreeChartModel> serverLIst = new ArrayList<TreeChartModel>();
		servers.forEach((server) -> {
			List<TreeChartModel> list = new ArrayList<TreeChartModel>();

			List<TreeChartModel> childDevices = new ArrayList<TreeChartModel>();
			server.getDevices().forEach((each) -> {
				TreeChartModel device = new TreeChartModel(each.getName());
				childDevices.add(device);
			});
			TreeChartModel devices = new TreeChartModel("Devices", childDevices);
			list.add(devices);
			if (childDevices.size() > 0)
				list.add(devices);

			List<TreeChartModel> childBrowsers = new ArrayList<TreeChartModel>();
			server.getBrowsers().forEach((each) -> {
				TreeChartModel brow = new TreeChartModel(each.getName());
				childBrowsers.add(brow);
			});
			TreeChartModel browsers = new TreeChartModel("Browsers", childBrowsers);
			if (childBrowsers.size() > 0)
				list.add(browsers);
			if (list.size() > 0) {
				TreeChartModel child = new TreeChartModel(server.getName(), list);
				serverLIst.add(child);
			}

		});
		tree.setChildren(serverLIst);
		return tree;
	}

	private TreeChartModel getClients() {
		List<Client> clients = clientService.getAll();
		TreeChartModel tree = new TreeChartModel();
		tree.setName("Clients");
		List<TreeChartModel> clientLIst = new ArrayList<TreeChartModel>();
		clients.forEach((client) -> {
			List<TreeChartModel> list = new ArrayList<TreeChartModel>();

			List<TreeChartModel> childDevices = new ArrayList<TreeChartModel>();
			client.getDevices().forEach((each) -> {
				TreeChartModel device = new TreeChartModel(each.getName());
				childDevices.add(device);
			});
			TreeChartModel devices = new TreeChartModel("Devices", childDevices);
			if (childDevices.size() > 0)
				list.add(devices);

			List<TreeChartModel> childBrowsers = new ArrayList<TreeChartModel>();
			client.getBrowsers().forEach((each) -> {
				TreeChartModel brow = new TreeChartModel(each.getName());
				childBrowsers.add(brow);
			});
			TreeChartModel browsers = new TreeChartModel("Browsers", childBrowsers);
			if (childBrowsers.size() > 0)
				list.add(browsers);
			if (list.size() > 0) {
				TreeChartModel child = new TreeChartModel(client.getName(), list);
				clientLIst.add(child);
			}

		});
		tree.setChildren(clientLIst);
		return tree;
	}

}
