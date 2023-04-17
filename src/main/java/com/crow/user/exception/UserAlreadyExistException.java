package com.crow.user.exception;


public class UserAlreadyExistException extends RuntimeException{
	private String message;

	public UserAlreadyExistException(String message) {
		super(message);
	}
	
}
