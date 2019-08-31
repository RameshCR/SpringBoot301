package com.restaurant.management.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;

public interface RestaurantService {

	/**
	 * Gets the restaurant based on input passed
	 * 
	 * @param property
	 * @param values
	 * @param pageRequest
	 * @return
	 */
	Page<Restaurant> getRestaurants(RestaurantSearchProperty property, List<String> values, Pageable pageRequest);
}
