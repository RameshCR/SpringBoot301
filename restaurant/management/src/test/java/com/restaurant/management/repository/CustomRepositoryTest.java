package com.restaurant.management.repository;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;

public class CustomRepositoryTest extends CustomRepository {

	@Mock
	private RestaurantRepository restaurantRepository;

	@Mock
	private CustomRepository customRepository;

	@InjectMocks
	private CustomRestaurantRepositoryImpl customRestaurantRepository;

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

	@Test(expected = NullPointerException.class)
	public void searchRestaurantPositiveTest1() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		StringBuilder queryString = new StringBuilder("SELECT r FROM Restaurant r ");
		TypedQuery<Restaurant> query = createQuery(queryString.toString(), Restaurant.class);
		Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantList);
		Mockito.when(query.getResultList()).thenReturn(restaurantList);
		customRestaurantRepository.searchRestaurant(RestaurantSearchProperty.NAME, values, pageRequest);
	}
	
	@Test
	public void searchRestaurantPositiveTest2() {
		List<String> values = new ArrayList<>();
		values.add("MTR");
		PageRequest pageRequest = PageRequest.of(1, 5);
		Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantList);
		assertNotNull(customRestaurantRepository.searchRestaurant(null, null, pageRequest));
	}
}
