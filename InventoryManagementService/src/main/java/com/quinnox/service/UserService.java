package com.quinnox.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quinnox.model.AuthResponse;
import com.quinnox.model.User;
import com.quinnox.repository.UserRepository;
import com.quinnox.utils.JwtTokenUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;

	public Object save(User user) {

		Map<String, String> map = new HashMap<>();

		Optional<User> exist = userRepo.findByUserName(user.getUsername());
		String password = user.getPassword();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		if (exist.isPresent()) {
//			exist.get().setPassword(encodedPassword);
//			userRepo.save(exist.get());
			map.put("message", "User: " + user.getUsername() + " already exists");

		} else {
			user.setPassword(encodedPassword);
			userRepo.save(user);

			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));

			User userModel = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(userModel);
			return new AuthResponse(userModel.getUsername(), userModel.getRole(), accessToken,
					"User: " + user.getUsername() + " created successfully");
		}
		return map;

	}

	public AuthResponse authenticate(String userName, String password) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

		User user = (User) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(user);
		AuthResponse response = new AuthResponse(user.getUsername(), user.getRole(), accessToken, "login sucessful");
		return response;
	}
}
