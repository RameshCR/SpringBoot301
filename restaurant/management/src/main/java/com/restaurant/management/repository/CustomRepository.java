package com.restaurant.management.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class CustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	protected Query createQuery(String query) {
		return this.entityManager.createQuery(query);
	}

	protected <T> TypedQuery<T> createQuery(String query, Class<T> resultClass) {
		return this.entityManager.createQuery(query, resultClass);
	}
}
