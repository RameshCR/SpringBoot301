package com.restaurant.management.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;
import com.restaurant.management.exception.ApiException;
import com.restaurant.management.service.RestaurantSearchServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RestaurantSearchControllerTest {

	@Mock
	private RestaurantSearchServiceImpl restaurantService;

	@InjectMocks
	private RestaurantSearchController restaurantSearchController;

	Restaurant restaurant = null;
	List<Restaurant> restaurantList = null;
	Page<Restaurant> restaurants = null;

	@Before
	public void setup() {
		restaurantList = new ArrayList<>();

		restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setName("MTR");
		restaurantList.add(restaurant);

		restaurants = new PageImpl<>(restaurantList);
	}

	@Test
	public void restaurantSearchControllerPoitiveTest() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		Mockito.when(restaurantService.getRestaurants(ArgumentMatchers.any(RestaurantSearchProperty.class),
				ArgumentMatchers.anyList(), ArgumentMatchers.any(PageRequest.class))).thenReturn(restaurants);
		assertNotNull(restaurantSearchController.searchRestaurants(RestaurantSearchProperty.NAME.getDescription(), values, pageRequest));
	}

	@Test(expected = ApiException.class)
	public void restaurantSearchControllerNegativeTest1() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		Mockito.when(restaurantService.getRestaurants(ArgumentMatchers.any(RestaurantSearchProperty.class),
				ArgumentMatchers.anyList(), ArgumentMatchers.any(PageRequest.class)))
				.thenReturn(new PageImpl<>(new ArrayList<>()));
		restaurantSearchController.searchRestaurants("test", values, pageRequest);
	}
	
	@Test(expected = ApiException.class)
	public void restaurantSearchControllerNegativeTest2() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		Mockito.when(restaurantService.getRestaurants(ArgumentMatchers.any(RestaurantSearchProperty.class),
				ArgumentMatchers.anyList(), ArgumentMatchers.any(PageRequest.class)))
				.thenReturn(new PageImpl<>(new ArrayList<>()));
		restaurantSearchController.searchRestaurants(null, values, pageRequest);
	}
	@Test
	public void testEqualsAndHashCode() {
		Restaurant restaurant1 = new Restaurant();
		restaurant1.setId(1);
		Restaurant restaurant2 = new Restaurant();
		restaurant1.setId(2);
		assertNotEquals(restaurant1, restaurant2);
	}

	@Test
	public void testToString() {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setName("MTR");
		assertEquals(restaurant.toString(), restaurant.toString());

	}
}
