package com.crow.user.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crow.user.dto.UpdateRequest;
import com.crow.user.dto.SigninRequest;
import com.crow.user.dto.SignupDto;
import com.crow.user.dto.UserUpdateDto;
import com.crow.user.exception.NotAuthorizedException;
import com.crow.user.exception.UserAlreadyExistException;
import com.crow.user.model.EAccess;
import com.crow.user.model.User;
import com.crow.user.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(SignupDto signupDto) {
		User user = User.builder().id(UUID.randomUUID()).name(signupDto.getName()).otherName(signupDto.getOtherName())
				.userName(signupDto.getUserName()).password("update required").roles(signupDto.getRoles())
				.access(EAccess.ON).build();
		return userRepository.save(user);
	}

	public boolean isUser(String userName) {
		return userRepository.findByUserName(userName).isEmpty();
	}

	@Override
	public List<User> get() {
		return userRepository.findAll();
	}

	@Override
	public User signin(SigninRequest signinRequest) throws UserAlreadyExistException {
		Optional<User> user = userRepository.findByUserName(signinRequest.getUserName());
		if (!user.isPresent()) {
			throw new NotAuthorizedException("User not found.");
		}
		if (user.get().getAccess().equals(EAccess.OFF)) {
			throw new NotAuthorizedException("Access prohibited by admin");
		} 
		if (user.get().getPassword().equals(signinRequest.getPassword())) {
			return user.get();
		} else {
			throw new NotAuthorizedException("Not authorized.");
		}

	}

	@Override
	public User update(UserUpdateDto userUpdateDto) {
		Optional<User> user = userRepository.findByUserName(userUpdateDto.getUserName());
		if (!user.isPresent()) {
			throw new NotAuthorizedException("User not found.");
		} else {
			if (user.get().getId().equals(UUID.fromString(userUpdateDto.getId()))) {
				user.get().setUserName(userUpdateDto.getUserName());
				user.get().setPassword(userUpdateDto.getPassword());
				userRepository.save(user.get());
				return user.get();
			} else {
				throw new NotAuthorizedException("Code or User Name is wrong. Please try again.");
			}
		}
	}

	@Override
	public User changeRole(UpdateRequest roleUpdateRequest) {
		User user = User.builder()
				.id(roleUpdateRequest.getId())
				.name(roleUpdateRequest.getName())
				.otherName(roleUpdateRequest.getOtherName())
				.userName(roleUpdateRequest.getUserName())
				.password(roleUpdateRequest.getPassword())
				.roles(roleUpdateRequest.getRoles())
				.access(roleUpdateRequest.getAccess()).build();
		return userRepository.save(user);
	}

	@Override
	public void delete(UpdateRequest roleUpdateRequest) {
		userRepository.deleteById(roleUpdateRequest.getId());
	}
}
