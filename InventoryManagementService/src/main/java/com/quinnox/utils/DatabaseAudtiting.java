package com.quinnox.utils;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.quinnox.model.User;

import java.util.Optional;

@Component
public class DatabaseAudtiting implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		String name = "unnamed";

		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			name = user.getUsername();
		}

		return Optional.of(name);
	}

}
