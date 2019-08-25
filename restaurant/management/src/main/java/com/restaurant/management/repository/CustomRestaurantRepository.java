package com.restaurant.management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;

@Repository
public interface CustomRestaurantRepository {

	Page<Restaurant> searchRestaurant(RestaurantSearchProperty property, List<String> values, Pageable pageResuest);
}
