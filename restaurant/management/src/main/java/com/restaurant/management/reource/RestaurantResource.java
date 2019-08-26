package com.restaurant.management.reource;

import java.io.Serializable;
import java.math.BigDecimal;

import com.restaurant.management.entity.Restaurant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class RestaurantResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer restaurantId;
	private String restaurantName;
	private String location;
	private Integer distance;
	private BigDecimal budget;
	private BigDecimal rating;
	private String cuisineType;
	private String contactNo;

	public RestaurantResource(Restaurant restaurant) {
		this.restaurantId = restaurant.getId();
		this.restaurantName = restaurant.getName();
		this.location = restaurant.getLocation();
		this.distance = restaurant.getDistance();
		this.budget = restaurant.getBudget();
		this.rating = restaurant.getRating();
		this.contactNo = restaurant.getContactNo();
		this.cuisineType = restaurant.getCuisineType();
	}

}
