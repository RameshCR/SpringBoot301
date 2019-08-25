package com.restaurant.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired private CustomRestaurantRepository customRestaurantRepository;

	@Override
	public Page<Restaurant> searchRestaurant(RestaurantSearchProperty property, List<String> values, Pageable pageRequest) {		
		return customRestaurantRepository.searchRestaurant(property, values, pageRequest);
	}

}
