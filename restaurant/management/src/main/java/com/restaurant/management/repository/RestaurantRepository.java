package com.restaurant.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entity.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

//	Page<Restaurant> findByName(String name);

}
