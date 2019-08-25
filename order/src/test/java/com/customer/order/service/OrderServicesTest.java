package com.customer.order.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.customer.order.repository.OrderDetailRepository;
import com.customer.order.repository.RestaurantRepository;
import com.customer.order.resource.PlaceOrderReource;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class OrderServicesTest {

	@Mock
	private OrderDetailRepository orderDetailRepository;

	@Mock
	private RestaurantRepository restaurantRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

	Customer customer = null;
	OrderDetail orderDetail = null;
	Restaurant restaurant = null;
	FoodItem foodItem = null;
	List<OrderDetail> orders = new ArrayList<>();
	OrderItem orderItem = null;
	OrderedItems items = null;

	Optional<Restaurant> optRestaurant = null;
	Optional<OrderDetail> optOrderDetail = null;

	@Before
	public void setup() {
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

		optRestaurant = Optional.ofNullable(restaurant);

		foodItem = new FoodItem();
		foodItem.setId(1);
		foodItem.setItemName("Pizza");
		foodItem.setItemPrice(BigDecimal.valueOf(200.00));

		orderDetail.setId(1);
		orderDetail.setOrderStatus(OrderStatus.PLACED);
		orderDetail.setCustomer(customer);
		orderDetail.setRestaurant(restaurant);
		orderDetail.setFoodItem(foodItem);

		optOrderDetail = Optional.ofNullable(orderDetail);

		orderItem = new OrderItem();
		orderItem.setItemName("Pizza");
		orderItem.setItemPrice(BigDecimal.valueOf(200.00));
		items.add(orderItem);

		orderDetail.setOrderedItems(items);
		orders.add(orderDetail);
	}

	@Test
	public void placeOrderPostiveTest() {
		PlaceOrderReource resource = new PlaceOrderReource();
		resource.setRestaurantId(1);

		Customer customer = new Customer();
		customer.setId(1);
		customer.setActive(true);

		Mockito.when(restaurantRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optRestaurant);
		Mockito.when(orderDetailRepository.save(ArgumentMatchers.any())).thenReturn(orderDetail);
		assertNotNull(orderService.placeOrder(resource, customer));
	}

	@Test
	public void updateOrderPositiveTest() {
		PlaceOrderReource resource = new PlaceOrderReource();
		resource.setRestaurantId(1);

		Customer customer = new Customer();
		customer.setId(1);
		customer.setActive(true);

		Mockito.when(orderDetailRepository.findActiveOrderByCustomerIdAndOrderId(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt())).thenReturn(orderDetail);
		Mockito.when(restaurantRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optRestaurant);
		assertNotNull(orderService.updateOrder(1, 1, resource));
	}

	@Test
	public void cancelOrderPostiveTest() {
		Mockito.when(orderDetailRepository.findActiveOrderByCustomerIdAndOrderId(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt())).thenReturn(orderDetail);
		assertNotNull(orderService.cancelOrder(1, 1));
	}

	@Test
	public void getAllOrderDetailsPostiveTest() {
		Customer customer = new Customer();
		customer.setId(1);

		Mockito.when(orderDetailRepository.findByCustomerAndIsActiveTrue(ArgumentMatchers.any())).thenReturn(orders);
		assertNotNull(orderService.getAllOrderDetails(customer));
	}

	@Test
	public void getOrderDetatailPositiveTest() {
		Mockito.when(orderDetailRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optOrderDetail);
		assertNotNull(orderService.getOrderDetatail(1));
	}
}
