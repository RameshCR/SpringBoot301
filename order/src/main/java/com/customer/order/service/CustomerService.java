package com.customer.order.service;

import com.customer.order.entity.Customer;

public interface CustomerService {

	/**
	 * Gets the customer
	 * 
	 * @param customerId
	 * @return
	 */
	Customer getCustomer(Integer customerId);
}
