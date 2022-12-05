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

import com.quinnox.model.Browser;
import com.quinnox.model.Server;
import com.quinnox.service.BrowserService;
import com.quinnox.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/inventory/v1")
public class BrowserController {

	@Autowired
	private BrowserService browserService;

	@GetMapping("/getAllBrowsers")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Browser> getAllBrowsers() {
		return browserService.getAll();
	}
	
	@GetMapping("/getAvailableBrowsers")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Browser> getAvailableBrowsers() {
		return browserService.getAvailBrowsers();
	}

	@PostMapping("/addBrowser")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Browser addBrowser(@RequestBody Browser browser) {
		return browserService.addBrowser(browser);
	}

	@PostMapping("/addMultipleBrowser")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public List<Browser> addBrowsers(@RequestBody List<Browser> browsers) {
		return browserService.addBrowsers(browsers);
	}

	@PutMapping("/updateBrowser")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Browser updateBrowser(@RequestBody Browser browser) throws Exception {
		return browserService.addBrowser(browser);
	}
	
	@GetMapping("/getBrowser/{browserId}")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public Browser getBrowser(@PathVariable String browserId) throws Exception{
		return browserService.getBrowser(browserId);
	}
	
	@DeleteMapping("/deleteBrowser/{browserId}")
	@RolesAllowed("ROLE_ADMIN")
	public void deleteBrowser(@PathVariable String browserId) throws Exception{
		browserService.deleteBrowser(browserId);
	}
	
	@GetMapping("/getAllBrowsersCount")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllBrowsersCount() throws Exception {
		return browserService.getAllBrowsersCount();
	}
	
	@GetMapping("/getAllBrowsersCountByState")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public JSONObject getAllBrowsersCountByStatus() throws Exception {
		return browserService.getAllBrowsersCountByState();
	}
	

	@GetMapping("/getAllBrowsersCountByNameAndVersion")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public org.json.simple.JSONObject getAllBrowsersCountByNameAndVersion() throws Exception {
		return browserService.getAllBrowsersCountByNameAndVersion();
	}


}
