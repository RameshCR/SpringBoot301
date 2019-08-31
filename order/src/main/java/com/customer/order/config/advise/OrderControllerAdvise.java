package com.customer.order.config.advise;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.customer.order.exception.ApiException;
import com.customer.order.exception.ErrorResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderControllerAdvise {

	public static URI getLocation(Integer identifier) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(identifier).toUri();
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResource> handleApiException(ApiException exception) {
		if (log.isDebugEnabled()) {
			log.debug("ApiException with status {}: ", exception.getStatus(), exception);
		} else {
			log.info("ApiException with status {}: {}", exception.getStatus(), exception.getMessage());
		}
		return exception.getResponse();
	}

}
