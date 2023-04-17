package com.crow.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Registration.Signing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.crow.user.dto.MessageResponse;
import com.crow.user.dto.UpdateRequest;
import com.crow.user.dto.SigninRequest;
import com.crow.user.dto.SignupDto;
import com.crow.user.service.IUserService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping("/crow/user/")
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private final IUserService userService;

	@PostMapping({"/signup"})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody SignupDto signupDto) {
		userService.create(signupDto);
		return new ResponseEntity<>(new MessageResponse("User Created Successfully!"), HttpStatus.CREATED);
	}
	
	@GetMapping({"/all"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(userService.get(), HttpStatus.CREATED);
	}
	
	@PostMapping({"/signin"})
	public ResponseEntity<?> login(@Valid @RequestBody SigninRequest signinRequest) {
		return new ResponseEntity<>(userService.signin(signinRequest), HttpStatus.ACCEPTED);
	}
	
	@PutMapping({"update"})
	public ResponseEntity<?> update(@Valid @RequestBody UpdateRequest updateRequest) {
		userService.changeRole(updateRequest);
		return new ResponseEntity<>(new MessageResponse("User Updated Successfully!"), HttpStatus.OK);
	}
	
	@PostMapping({"delete"})
	public ResponseEntity<?> delete(@Valid @RequestBody UpdateRequest roleUpdateRequest) {
		userService.delete(roleUpdateRequest);
		return new ResponseEntity<>(new MessageResponse("User Deleted Successfully!"), HttpStatus.OK);
	}
	
}
