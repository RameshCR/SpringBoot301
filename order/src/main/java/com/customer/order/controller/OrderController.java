package com.customer.order.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.order.entity.Customer;
import com.customer.order.entity.OrderDetail;
import com.customer.order.exception.ApiException;
import com.customer.order.resource.BillDetailResource;
import com.customer.order.resource.PlaceOrderReource;
import com.customer.order.resource.ViewOrderResource;
import com.customer.order.service.CustomerService;
import com.customer.order.service.OrderService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/{customerId}")
@Api(value="Everything about Order Info", tags = {"Order Service"})
public class OrderController {

	@Autowired OrderService orderService;
	@Autowired CustomerService customerService;

	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@PathVariable Integer customerId,
			@RequestBody PlaceOrderReource orderResource) {
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Customer Not Found");
		}
		log.debug("Placing the order for customer: " + customer.getId());
		OrderDetail orderDetail = orderService.placeOrder(orderResource, customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDetail.getId());
	}

	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<?> updateOrder(@PathVariable Integer customerId, @PathVariable Integer orderId,
			@RequestBody PlaceOrderReource orderResource) {
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Customer Not Found");
		}
		OrderDetail orderDetail = orderService.getOrderDetatail(orderId);
		if (orderDetail == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Order Not Found");
		}
		log.debug("Updating the orders for customer: {} Order :{}", customer.getId(), orderId);
		OrderDetail updatedOrder = orderService.updateOrder(customerId, orderId, orderResource);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedOrder.getId());
	}

	@GetMapping("/viewOrder")
	public List<ViewOrderResource> viewOrder(@PathVariable Integer customerId) {
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Custome Not Found");
		}
		log.debug("fetching the orders for customer: " + customer.getId());
		List<OrderDetail> orderDetails = orderService.getAllOrderDetails(customer);
		if (CollectionUtils.isEmpty(orderDetails)) {
			throw new ApiException(HttpStatus.NOT_FOUND, "No Order exists");
		} else {
			return orderDetails.stream()
					.map(ViewOrderResource::new)
					.collect(Collectors.toList());
		}
	}

	@DeleteMapping("/cancelOrder/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable Integer customerId, @PathVariable Integer orderId) {
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Custome Not Found");
		}
		OrderDetail orderDetail = orderService.getOrderDetatail(orderId);
		if (orderDetail == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Order Not Found");
		}
		OrderDetail cancelledOrder = orderService.cancelOrder(customerId, orderId);
		return ResponseEntity.status(HttpStatus.OK).body(cancelledOrder.getId());
	}
	
	@GetMapping("/totalBill/{orderId}")
	public BillDetailResource getOrderBill(@PathVariable Integer customerId, @PathVariable Integer orderId){
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Custome Not Found");
		}
		OrderDetail orderDetail = orderService.getOrderDetatail(orderId);
		if (orderDetail == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Order Not Found");
		}
		log.debug("Getting bill for Order :" + orderId);
		return new BillDetailResource(orderDetail);
	}
}
