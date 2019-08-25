package com.customer.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customer.order.entity.FoodItem;

@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Integer> {

}
