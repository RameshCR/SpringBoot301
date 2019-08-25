package com.customer.order.resource;

import java.io.Serializable;
import java.math.BigDecimal;

import com.customer.order.entity.OrderedItems.OrderItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderItemsComponent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemName;
	private BigDecimal itemPrice;

	public OrderItemsComponent(OrderItem item) {
		this.itemName = item.getItemName();
		this.itemPrice = item.getItemPrice();
	}
}
