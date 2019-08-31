package com.restaurant.management.exception;

import java.util.List;

import com.restaurant.management.enums.ApiErrorCode;

import lombok.Data;

@Data
public class ErrorResource {

	private int status; // The http status code
	private String code; // The internal, readable code
	private String message;
	private List<ErrorDetailComponent> details;

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

	public ErrorResource(ApiErrorCode apiError, List<ErrorDetailComponent> details) {
		this(apiError);
		this.details = details;
	}

}
