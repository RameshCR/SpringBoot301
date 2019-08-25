package com.restaurant.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.management.enums.RestaurantSearchProperty;
import com.restaurant.management.reource.RestaurantResource;
import com.restaurant.management.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestaurantSearchController {

	@Autowired private RestaurantService restaurantService;

	@GetMapping("/searchRestaurant")
	public Page<RestaurantResource> searchRestaurants(@RequestParam(required = false) RestaurantSearchProperty searchProperty, @RequestParam(required = false) List<String> values, Pageable pageRequest) {
		log.debug("searching restaurant based on name: {} ", searchProperty);
		return restaurantService.searchRestaurant(searchProperty, values, pageRequest).map(RestaurantResource::new);
	}
}
