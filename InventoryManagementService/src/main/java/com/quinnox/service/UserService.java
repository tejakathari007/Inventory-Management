package com.quinnox.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quinnox.model.User;
import com.quinnox.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String save(User user) {

		Optional<User> exist = userRepo.findByUserName(user.getUsername());
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		if (exist.isPresent()) {
			exist.get().setPassword(encodedPassword);
			userRepo.save(exist.get());
			return "User: " + user.getUsername() + " already present, updated with new password";

		} else {
			user.setPassword(encodedPassword);
			userRepo.save(user);
			return "User: " + user.getUsername() + " created successfully";
		}
	}
}
