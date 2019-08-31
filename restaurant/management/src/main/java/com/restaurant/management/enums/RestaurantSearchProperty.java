package com.restaurant.management.enums;

import org.apache.commons.lang.StringUtils;

public enum RestaurantSearchProperty {
	NAME("name"), LOCATION("location"), CUISINE("cuisine");

	private final String description;

	public String getDescription() {
		return description;
	}

	private RestaurantSearchProperty(String description) {
		this.description = description;
	}

	public static RestaurantSearchProperty getByPropertyName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (RestaurantSearchProperty memberSearchProperty : RestaurantSearchProperty.values()) {
			if (memberSearchProperty.getDescription().equals(name)) {
				return memberSearchProperty;
			}
		}
		return null;
	}

}
