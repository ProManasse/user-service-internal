package com.crow.user.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank(message = "User Code is required")
	private String id;
	@NotBlank(message = "User Name is required")
	private String userName;
	@NotBlank(message = "Password is required")
	private String password;
}
