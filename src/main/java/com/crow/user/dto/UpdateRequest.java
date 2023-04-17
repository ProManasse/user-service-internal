package com.crow.user.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.crow.user.model.EAccess;
import com.crow.user.model.ERole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateRequest {
	private UUID id;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "OtherName is mandatory")
	private String otherName;
	@NotBlank(message = "UserName is mandatory")
	private String userName;
	@NotBlank(message = "Password is mandatory")
	private String password;
	private List<ERole> roles;
	private EAccess access;
}
