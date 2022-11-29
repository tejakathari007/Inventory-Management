package com.quinnox.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	@NotNull
	@Length(min = 1, max = 50)
	private String userName;

	@NotNull
	@Length(min = 1, max = 64)
	private String password;


}
