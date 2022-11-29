package com.quinnox.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.Visitor;
import com.quinnox.service.VisitorService;

@CrossOrigin
@RestController
@RequestMapping(value = "/inventory/v1")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;

	@GetMapping("/audits")
	@RolesAllowed("ROLE_ADMIN")
	public List<Visitor> getAllVisitors() {
		return visitorService.getAll();
	}
}
