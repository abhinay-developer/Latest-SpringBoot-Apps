package com.abhi.user.management.exception;

import lombok.Data;

@Data
public class EmailAlreadyExistsException extends Exception {

	public EmailAlreadyExistsException(String message) {
		super(message);
	}

}
