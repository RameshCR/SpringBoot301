package com.customer.order.service;

import java.util.List;

import com.customer.order.entity.Customer;
import com.customer.order.entity.OrderDetail;
import com.customer.order.resource.PlaceOrderReource;

public interface OrderService {
	/**
	 * Places the order for customer
	 * 
	 * @param orders
	 * @param customer
	 * @return
	 */
	OrderDetail placeOrder(PlaceOrderReource orders, Customer customer);

	/**
	 * Updates the customer order
	 * 
	 * @param customerId
	 * @param orderId
	 * @param orderResource
	 */
	void updateOrder(Integer customerId, Integer orderId, PlaceOrderReource orderResource);

	/**
	 * Cancels the customer order
	 * 
	 * @param customerId
	 * @param orderId
	 */
	void cancelOrder(Integer customerId, Integer orderId);

	/**
	 * Gets all active order for customer
	 * 
	 * @param customer
	 * @return
	 */
	List<OrderDetail> getAllOrderDetails(Customer customer);

	/**
	 * Gets order detail
	 * 
	 * @param orderId
	 * @return
	 */
	OrderDetail getOrderDetatail(Integer orderId);
}
