package com.example.exceptionsHandlers;

import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.hibernate.tool.schema.spi.ExceptionHandler;

import com.example.user_package.User;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
