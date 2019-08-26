package com.restaurant.management.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.restaurant.management.enums.ApiErrorCode;

public class ApiException extends RuntimeException {

	private HttpStatus status;
	private String code;

	public ApiException(ApiErrorCode apiErrorCode) {
		this(apiErrorCode, apiErrorCode.getMessage());
	}

	public ApiException(ApiErrorCode apiErrorCode, String message) {
		this(apiErrorCode.getHttpStatus(), apiErrorCode.getCode(), message);
	}

	public ApiException(int status, String code, String message) {
		super(Optional.ofNullable(message).orElse(""));
		this.status = HttpStatus.valueOf(status);
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	private static final long serialVersionUID = 1L;
}
