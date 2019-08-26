package com.restaurant.management.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entity.Restaurant;
import com.restaurant.management.enums.RestaurantSearchProperty;

@Repository
public class CustomRestaurantRepositoryImpl extends CustomRepository implements CustomRestaurantRepository {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public Page<Restaurant> searchRestaurant(RestaurantSearchProperty property, List<String> values,
			Pageable pageResuest) {
		if (property == null) {
			List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
			return new PageImpl<>(restaurantList, pageResuest, restaurantList.size());
		} else {
			StringBuilder queryString = new StringBuilder("SELECT r FROM Restaurant r ");
			switch (property) {
			case NAME:
				queryString.append(" WHERE r.name IN (:values) ");
				break;
			case LOCATION:
				queryString.append(" WHERE r.location IN (:values) ");
				break;
			case DISTANCE:
				queryString.append(" WHERE r.distance IN (:values) ");
				break;
			case BUDGET:
				queryString.append(" WHERE r.budget IN (:values) ");
				break;
			case RATING:
				queryString.append(" WHERE r.rating IN (:values) ");
				break;
			case CUISINE:
				queryString.append(" WHERE r.cuisineType IN (:values) ");
				break;
			default:
				break;
			}

			TypedQuery<Restaurant> query = createQuery(queryString.toString(), Restaurant.class);
			query.setParameter("values", values);
			query.setFirstResult((int) pageResuest.getOffset());
			query.setMaxResults(pageResuest.getPageSize());
			
			return new PageImpl<>(query.getResultList());
		}
	}
}
