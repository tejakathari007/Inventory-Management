package com.quinnox.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.User;
import com.quinnox.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/inventory/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public String createUser(@RequestBody @Valid User user) {
		return userService.save(user);
	}
}
