package com.crow.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SigninRequest {
    @NotNull
    @NotEmpty
	private String userName;
    @NotNull
    @NotEmpty
	private String password;
}
