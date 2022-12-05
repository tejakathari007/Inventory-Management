package com.quinnox.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.Client;
import com.quinnox.model.ClientBrowser;
import com.quinnox.model.ClientDevice;
import com.quinnox.model.EmailModel;
import com.quinnox.service.ClientService;
import com.quinnox.service.EmailService;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/inventory/v1")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private ClientService service;

	@PostMapping("/triggerMail")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public String addBrowser(@RequestBody EmailModel emailModel) throws Exception {
		Client client = service.getClient(emailModel.getClientId());
		Map<String, Object> model = new HashMap<>();
		model.put("name", client.getName());
		model.put("browsers", client.getBrowsers().size());
		model.put("devices", client.getDevices().size());

		model.put("engagement", client.getEngagement());
		model.put("ClientSubscriptionStartDate", client.getContractStartDate().toString());
		model.put("ClientSubscriptionEndDate", client.getContractEndDate().toString());
		String browsers = "";
		for (ClientBrowser s : client.getBrowsers()) {
			browsers = browsers + s.getName()+"_"+s.getVersion() + ",";
		}
		model.put("browserList", browsers);
		String deviceList = "";
		for (ClientDevice s : client.getDevices()) {
			deviceList = deviceList + s.getName() + ",";
		}
		model.put("deviceList", deviceList);
		String mails = "";
		if (emailModel.getEmails() != null) {
			mails = emailModel.getEmails();
		} else {
			mails = client.getEmail();
		}
		return emailService.sendEmail(model, mails);
	}

}
