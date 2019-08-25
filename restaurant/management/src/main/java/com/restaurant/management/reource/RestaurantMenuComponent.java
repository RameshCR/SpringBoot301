package com.restaurant.management.reource;

import java.math.BigDecimal;
import java.util.HashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class RestaurantMenuComponent {
	private HashMap<String, BigDecimal> starter;
	private HashMap<String, BigDecimal> mainCourse;
	private HashMap<String, BigDecimal> desserts;
	private HashMap<String, BigDecimal> softDrinks;

}
