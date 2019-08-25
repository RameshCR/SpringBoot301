package com.customer.order.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
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

import com.customer.order.entity.Customer;
import com.customer.order.entity.FoodItem;
import com.customer.order.entity.OrderDetail;
import com.customer.order.entity.OrderedItems;
import com.customer.order.entity.OrderedItems.OrderItem;
import com.customer.order.entity.Restaurant;
import com.customer.order.enums.OrderStatus;
import com.customer.order.resource.PlaceOrderReource;
import com.customer.order.service.CustomerService;
import com.customer.order.service.OrderService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderService orderService;
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	 private OrderController orderController;
	
	Customer customer = null;
	OrderDetail orderDetail = null;
	Restaurant restaurant = null;
	FoodItem foodItem = null;
	List<OrderDetail> orders = new ArrayList<>();
	OrderItem orderItem = null;
	OrderedItems items = null;
	
	@Before
	public void setup(){
		orderDetail = new OrderDetail();
		items = new OrderedItems();
		customer = new Customer();
		customer.setId(1);
		customer.setFirstName("Ramesh");
		customer.setPhoneNo("9743192527");
		
		restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setName("MTR");
		restaurant.setLocation("Indira Nagara");
		
		foodItem = new FoodItem();
		foodItem.setId(1);
		foodItem.setItemName("Pizza");
		foodItem.setItemPrice(BigDecimal.valueOf(200.00));
		
		orderDetail.setId(1);
		orderDetail.setOrderStatus(OrderStatus.PLACED);
		orderDetail.setCustomer(customer);
		orderDetail.setRestaurant(restaurant);
		orderDetail.setFoodItem(foodItem);	
		
		orderItem = new OrderItem();
		orderItem.setItemName("Pizza");
		orderItem.setItemPrice(BigDecimal.valueOf(200.00));
		items.add(orderItem);
		
		orderDetail.setOrderedItems(items);
		orders.add(orderDetail);
	}

	@Test
	public void placeOrderPositiveTest(){
		PlaceOrderReource orderResource = new PlaceOrderReource();
		orderResource.setRestaurantId(1);
		
		Mockito.when(orderService.placeOrder(ArgumentMatchers.any(PlaceOrderReource.class), ArgumentMatchers.any(Customer.class)))
		.thenReturn(orderDetail);
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		assertNotNull(orderController.placeOrder(1, orderResource));		
	}
	
//	@Test
	public void placeOrderNegativeTest(){
		PlaceOrderReource orderResource = new PlaceOrderReource();
		orderResource.setRestaurantId(1);
		
		Mockito.when(orderService.placeOrder(ArgumentMatchers.any(PlaceOrderReource.class), ArgumentMatchers.any(Customer.class)))
		.thenReturn(orderDetail);
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		assertNull(orderController.placeOrder(10, orderResource));
	}
	
	@Test
	public void updateOrderPositiveTest(){
		PlaceOrderReource orderResource = new PlaceOrderReource();
		orderResource.setRestaurantId(1);
		
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		Mockito.when(orderService.getOrderDetatail(ArgumentMatchers.anyInt())).thenReturn(orderDetail);
		Mockito.when(orderService.updateOrder(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(PlaceOrderReource.class))).thenReturn(orderDetail);
		
		assertNotNull(orderController.updateOrder(1, 1, orderResource));
	}
	
	
	@Test
	public void viewOrderPositiveTest(){
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		Mockito.when(orderService.getAllOrderDetails(ArgumentMatchers.any(Customer.class))).thenReturn(orders);		
		assertNotNull(orderController.viewOrder(1));
	}
	
//	@Test
	public void viewOrderNegativeTest(){
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		Mockito.when(orderService.getAllOrderDetails(ArgumentMatchers.any(Customer.class))).thenReturn(orders);
		assertNull(orderController.viewOrder(1));
	}
	
	@Test
	public void cancelOrderPositiveTest(){
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		Mockito.when(orderService.getOrderDetatail(ArgumentMatchers.anyInt())).thenReturn(orderDetail);	
		Mockito.when(orderService.cancelOrder(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(orderDetail);
		assertNotNull(orderController.cancelOrder(1, 2));

	}
	
//	@Test
	public void cancelOrderNegativeTest(){
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		Mockito.when(orderService.getOrderDetatail(ArgumentMatchers.anyInt())).thenReturn(orderDetail);
		Mockito.when(orderService.cancelOrder(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(null);
		assertNull(orderController.cancelOrder(1, 1));
	}
	
	@Test
	public void getOrderBillPositiveTest(){
		Mockito.when(customerService.getCustomer(ArgumentMatchers.anyInt())).thenReturn(customer);
		Mockito.when(orderService.getOrderDetatail(ArgumentMatchers.anyInt())).thenReturn(orderDetail);	
		assertNotNull(orderController.getOrderBill(1, 1));
	}
	
}
