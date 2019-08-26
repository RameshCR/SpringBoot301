package com.restaurant.management.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.restaurant.management.repository.CustomRestaurantRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RestaurantSearchServiceTest {
	
	@Mock
	private CustomRestaurantRepository customRestaurantRepository;
	
	@InjectMocks
	private RestaurantServiceImpl restaurantService;

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
	
//	@Test
	public void searchRestaurantPostiveTest() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		Mockito.when(customRestaurantRepository.searchRestaurant(ArgumentMatchers.any(RestaurantSearchProperty.class), ArgumentMatchers.anyList(), ArgumentMatchers.any(PageRequest.class))).thenReturn(restaurants);
		assertNotNull(restaurantService.getRestaurants(RestaurantSearchProperty.NAME, values, pageRequest));		
	}
	
	
//	@Test
	public void searchRestaurantNegativeTest() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		Mockito.when(customRestaurantRepository.searchRestaurant(ArgumentMatchers.any(RestaurantSearchProperty.class), ArgumentMatchers.anyList(), ArgumentMatchers.any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
		assertNull(restaurantService.getRestaurants(RestaurantSearchProperty.NAME, values, pageRequest));		
	}
}
