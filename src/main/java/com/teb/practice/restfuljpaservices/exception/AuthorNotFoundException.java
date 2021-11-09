package com.teb.practice.restfuljpaservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2346471898736960202L;

	public AuthorNotFoundException(String message) {
		super(message.concat(", was not found."));
	}
}
