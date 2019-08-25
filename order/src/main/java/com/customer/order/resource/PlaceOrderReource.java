package com.customer.order.resource;

import java.io.Serializable;

import com.customer.order.entity.OrderedItems;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter@Setter
public class PlaceOrderReource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer restaurantId;
	private OrderedItems items;

}
