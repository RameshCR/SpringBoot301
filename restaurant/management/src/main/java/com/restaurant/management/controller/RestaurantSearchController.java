package com.restaurant.management.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.management.enums.ApiErrorCode;
import com.restaurant.management.enums.RestaurantSearchProperty;
import com.restaurant.management.exception.ApiException;
import com.restaurant.management.reource.RestaurantResource;
import com.restaurant.management.service.RestaurantSearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/searchRestaurant")
public class RestaurantSearchController {

	@Autowired	private RestaurantSearchService restaurantSearchService;

	@GetMapping
	public Page<RestaurantResource> searchRestaurants(@RequestParam(required = false) String searchProperty,
			@RequestParam(required = false) List<String> values, Pageable pageRequest) {

		RestaurantSearchProperty restaurantSearchProperty = RestaurantSearchProperty.getByPropertyName(searchProperty);
		if (!StringUtils.isBlank(searchProperty) && restaurantSearchProperty == null) {
			throw new ApiException(ApiErrorCode.BAD_PARAMETER, "searchProperty", "Invalid search property");
		}

		if ((searchProperty == null && !CollectionUtils.isEmpty(values))
				|| (searchProperty != null && CollectionUtils.isEmpty(values))) {
			throw new ApiException(ApiErrorCode.MISSING_PARAMETER, "Both property and values should be present.");
		}
		log.debug("searching restaurant based on property: {} and values: {} ", searchProperty, values);
		return restaurantSearchService.getRestaurants(restaurantSearchProperty, values, pageRequest)
				.map(RestaurantResource::new);
	}
}
