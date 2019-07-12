package com.ctol.bench.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends Exception {
	private static final long serialVersionUID = -8216691066065912972L;

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
