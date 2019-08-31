package com.restaurant.management.config.advise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.restaurant.management.exception.ApiException;
import com.restaurant.management.exception.ErrorResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestaurantControllerAdvise {
	
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
