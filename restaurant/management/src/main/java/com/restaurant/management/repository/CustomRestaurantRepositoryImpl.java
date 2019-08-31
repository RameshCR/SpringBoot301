package com.restaurant.management.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;

@Repository
public class CustomRestaurantRepositoryImpl extends CustomRepository implements CustomRestaurantRepository {

	@Autowired private RestaurantRepository restaurantRepository;

	public Page<Restaurant> searchRestaurant(RestaurantSearchProperty property, List<String> values,
			Pageable pageRequest) {
		System.out.println("Inside CustomRestaurantRepositoryImpl method()");
		if (property == null) {
			List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
			return new PageImpl<>(restaurantList, pageRequest, restaurantList.size());
		} else {
			StringBuilder queryString = new StringBuilder("SELECT r FROM Restaurant r ");
			switch (property) {
			case NAME:
				queryString.append(" WHERE r.name IN (:values) ");
				break;
			case LOCATION:
				queryString.append(" WHERE r.location IN (:values) ");
				break;
			case CUISINE:
				queryString.append(" WHERE r.cuisineType IN (:values) ");
				break;
			default:
				break;
			}

			TypedQuery<Restaurant> query = createQuery(queryString.toString(), Restaurant.class);
			query.setParameter("values", values);
			query.setFirstResult((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize());
			query.setMaxResults(pageRequest.getPageSize());

			return new PageImpl<>(query.getResultList());
		}
	}
}
