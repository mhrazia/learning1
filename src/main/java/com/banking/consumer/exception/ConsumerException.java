package com.banking.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ConsumerException extends RuntimeException {

	public ConsumerException(String exceptionInfo) {
		super(exceptionInfo);
	}
}
