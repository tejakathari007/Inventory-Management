package com.quinnox.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.EmailModel;
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

	@PostMapping("/triggerMail")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_EDITOR" })
	public String addBrowser(@RequestBody EmailModel emailModel) throws MessagingException, IOException, TemplateException {
		Map<String, Object> model = new HashMap<>();
		model.put("name", "TEJA");
		return emailService.sendEmail(model,emailModel.getEmails());
	}

}
