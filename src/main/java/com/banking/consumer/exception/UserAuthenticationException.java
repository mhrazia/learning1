package com.banking.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAuthenticationException extends RuntimeException {
	public UserAuthenticationException(String exceptionInfo) {
		super(exceptionInfo);
	}
}
