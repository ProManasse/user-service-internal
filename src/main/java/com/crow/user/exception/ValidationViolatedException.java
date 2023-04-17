package com.crow.user.exception;

import java.sql.SQLException;

public class ValidationViolatedException  extends SQLException{
	private String message;

	public ValidationViolatedException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
	
	
}
