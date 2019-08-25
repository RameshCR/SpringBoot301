package com.customer.order.resource;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.customer.order.entity.OrderDetail;
import com.customer.order.entity.OrderedItems;
import com.customer.order.enums.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ViewOrderResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String customerName;
	private String contactNo;
	private String restaurantName;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private OrderedItems items;
	
	public ViewOrderResource(OrderDetail order){
		this.customerName = order.getCustomer().getFirstName();
		this.contactNo = order.getCustomer().getPhoneNo();
		this.restaurantName = order.getRestaurant().getName();
		this.orderDate = order.getOrderDate();
		this.orderStatus = order.getOrderStatus();
		this.items = order.getOrderedItems();
	}
	
	
	

}
