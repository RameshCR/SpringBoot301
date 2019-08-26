package com.restaurant.management.enums;

public enum RestaurantSearchProperty {
	NAME("name"), LOCATION("location"), CUISINE("cuisine");

	private final String description;

	public String getDescription() {
		return description;
	}

	private RestaurantSearchProperty(String description) {
		this.description = description;
	}

}
