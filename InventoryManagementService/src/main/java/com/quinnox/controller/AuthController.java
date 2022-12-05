package com.quinnox.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.AuthRequest;
import com.quinnox.service.UserService;

@CrossOrigin
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {

			return ResponseEntity.ok().body(userService.authenticate(request.getUserName(), request.getPassword()));

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
