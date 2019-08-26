package com.restaurant.management.exception;

import com.restaurant.management.enums.ApiErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResource {

	private int status;
	private String code;
	private String message;

	public ErrorResource(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ErrorResource(ApiErrorCode apiError) {
		this.status = apiError.getHttpStatus();
		this.code = apiError.getCode();
		this.message = apiError.getMessage();
	}

	public ErrorResource(ApiErrorCode apiError, String message) {
		this(apiError);
		this.message = message;
	}

}
