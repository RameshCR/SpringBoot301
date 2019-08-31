package com.customer.order.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.customer.order.enums.ApiErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiException extends RuntimeException {

	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	private String code = ApiErrorCode.DEFAULT_500.getCode();
	private List<ErrorDetailComponent> details = new ArrayList<>();

	public ApiException(ApiErrorCode apiErrorCode) {
		this(apiErrorCode, apiErrorCode.getMessage());
	}

	public ApiException(ApiErrorCode apiErrorCode, String message) {
		this(apiErrorCode, message, new HashMap<>());
	}

	public ApiException(ApiErrorCode apiErrorCode, Map<String, String> details) {
		this(apiErrorCode, apiErrorCode.getMessage(), details);
	}

	public ApiException(ApiErrorCode apiErrorCode, String message, Map<String, String> details) {
		this(apiErrorCode.getHttpStatus(), apiErrorCode.getCode(), message, details);
	}

	public ApiException(int status, String code, String message, Map<String, String> details) {
		this(status, code, message, details.entrySet().stream()
				.map(es -> new ErrorDetailComponent(es.getKey(), es.getValue())).collect(Collectors.toList()));
	}

	public ApiException(ApiErrorCode apiErrorCode, String detailField, String detailMessage) {
		this(apiErrorCode, Arrays.asList(new ErrorDetailComponent(detailField, detailMessage)));
	}

	public ApiException(ApiErrorCode apiErrorCode, List<ErrorDetailComponent> details) {
		this(apiErrorCode.getHttpStatus(), apiErrorCode.getCode(), apiErrorCode.getMessage(), details);
	}

	public ApiException(int status, String code, String message, List<ErrorDetailComponent> details) {
		super(Optional.ofNullable(message).orElse(""));
		this.status = HttpStatus.valueOf(status);
		this.code = code;
		if (details != null) {
			this.details = details;
		}
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public ResponseEntity<ErrorResource> getResponse() {
		ErrorResource errorResource = new ErrorResource(status.value(), code, getMessage());
		errorResource.setDetails(details);
		return ResponseEntity.status(status).body(errorResource);
	}

	private static final long serialVersionUID = 1L;
}
