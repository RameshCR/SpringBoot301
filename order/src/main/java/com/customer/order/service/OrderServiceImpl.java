package com.customer.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.order.entity.Customer;
import com.customer.order.entity.FoodItem;
import com.customer.order.entity.OrderDetail;
import com.customer.order.entity.Restaurant;
import com.customer.order.enums.OrderStatus;
import com.customer.order.repository.OrderDetailRepository;
import com.customer.order.repository.RestaurantRepository;
import com.customer.order.resource.PlaceOrderReource;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

	@Autowired private OrderDetailRepository orderDetailRepository;
	@Autowired private RestaurantRepository restaurantRepository;

	@Override
	@Transactional
	public OrderDetail placeOrder(PlaceOrderReource order, Customer customer) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
		FoodItem item = new FoodItem();
		item.setId(2);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCustomer(customer);
		orderDetail.setOrderStatus(OrderStatus.PLACED);
		orderDetail.setOrderDate(LocalDateTime.now());
		orderDetail.setFoodItemCount(1);
		orderDetail.setFoodItem(item);
		orderDetail.setRestaurant(restaurant.get());
		orderDetail.setOrderedItems(order.getItems());
		orderDetail = orderDetailRepository.save(orderDetail);
		return orderDetail;
	}

	@Override
	public List<OrderDetail> getAllOrderDetails(Customer customer) {
		return orderDetailRepository.findByCustomerAndIsActiveTrue(customer);
	}

	@Override
	@Transactional
	public void updateOrder(Integer customerId, Integer orderId, PlaceOrderReource orderResource) {
		OrderDetail orderDetail = orderDetailRepository.findActiveOrderByCustomerIdAndOrderId(customerId, orderId);
		Restaurant restaurant = restaurantRepository.findById(orderResource.getRestaurantId()).get();
		orderDetail.setRestaurant(restaurant);
		orderDetail.setOrderDate(LocalDateTime.now());
		orderDetail.setOrderedItems(orderResource.getItems());
		orderDetailRepository.save(orderDetail);
	}

	@Override
	@Transactional
	public void cancelOrder(Integer customerId, Integer orderId) {
		OrderDetail orderDetail = orderDetailRepository.findActiveOrderByCustomerIdAndOrderId(customerId, orderId);
		orderDetail.setActive(false);
		orderDetail.setOrderStatus(OrderStatus.CANCELLED);
		orderDetailRepository.save(orderDetail);
	}

	@Override
	public OrderDetail getOrderDetatail(Integer orderId) {
		return Optional.ofNullable(orderDetailRepository.findById(orderId)).get().orElseGet(OrderDetail::new);
	}

}
