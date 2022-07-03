package com.banking.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TransactionException extends RuntimeException{
	public TransactionException(String exceptionInfo) {
		super(exceptionInfo);
	}
}
