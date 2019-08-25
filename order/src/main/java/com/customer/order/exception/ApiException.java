package com.customer.order.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final HttpStatus sttaus;

	public ApiException(HttpStatus status, String message) {
		this.sttaus = status;
		this.message = message;
	}

}
