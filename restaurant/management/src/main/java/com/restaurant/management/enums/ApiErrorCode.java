package com.restaurant.management.enums;

import java.util.EnumSet;

public enum ApiErrorCode {

	
	DEFAULT_400(400, "badRequest", "This is a bad request."),
	DEFAULT_500(500, "internalServerError", "internal server error");

	private final int httpStatus;
	private final String code;
	private final String message;

	private ApiErrorCode(int httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ApiErrorCode getDefaultByHttpStatus(int httpStatus) {
		return EnumSet.allOf(ApiErrorCode.class).stream()
				.filter(ec -> ec.getHttpStatus() == httpStatus && ec.name().contains("DEFAULT")).findFirst()
				.orElse(null);
	}
}
