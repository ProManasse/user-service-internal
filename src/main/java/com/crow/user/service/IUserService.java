package com.crow.user.service;

import java.util.List;

import com.crow.user.dto.UpdateRequest;
import com.crow.user.dto.SigninRequest;
import com.crow.user.dto.SignupDto;
import com.crow.user.dto.UserUpdateDto;
import com.crow.user.model.User;

public interface IUserService {
	User create(SignupDto signupDto);
	List<User> get();
	User signin(SigninRequest signinRequest);
	User update(UserUpdateDto userUpdateDto);
	User changeRole(UpdateRequest roleUpdateRequest);
	void delete(UpdateRequest roleUpdateRequest);
}
