package com.customer.order.exception;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorDetailComponent {

	private Integer objectId;
	private String field;
	private String message;

	public ErrorDetailComponent(FieldError fe) {
		this.field = fe.getField();
		this.message = fe.getDefaultMessage();
	}

	public ErrorDetailComponent(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public ErrorDetailComponent(Integer objectId, String field) {
		this.objectId = objectId;
		this.field = field;
	}
}
