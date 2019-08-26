package com.restaurant.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;
import com.restaurant.management.repository.CustomRestaurantRepository;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired	private CustomRestaurantRepository customRestaurantRepository;
	@Autowired	private RestaurantServiceImpl self;

	@Override
	public Page<Restaurant> getRestaurants(RestaurantSearchProperty property, List<String> values,
			Pageable pageRequest) {
		return get(property, values, pageRequest);
	}

	public Page<Restaurant> get(RestaurantSearchProperty property, List<String> values, Pageable pageRequest) {
		return self.getFromCache(property, values, pageRequest);
	}

	@Cacheable("restaurants")
	public Page<Restaurant> getFromCache(RestaurantSearchProperty property, List<String> values, Pageable pageRequest) {
		return customRestaurantRepository.searchRestaurant(property, values, pageRequest);
	}

}
