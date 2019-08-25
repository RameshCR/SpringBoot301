package com.customer.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.customer.order.entity.OrderedItems.OrderItem;
import com.customer.order.utils.JsonType;

import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderedItems extends ArrayList<OrderItem> {

	private static final long serialVersionUID = 1L;

	public OrderedItems() {
		super();
	}

	public OrderedItems(List<FoodItem> foodItems) {
		super(foodItems.stream().map(OrderItem::new).collect(Collectors.toList()));
	}

	public static class OrderedItemsType extends JsonType<OrderedItems> {
		public OrderedItemsType() {
			super(OrderedItems.class);
		}
	}

	@NoArgsConstructor
	@Data
	public static class OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private String itemName;
		private BigDecimal itemPrice;

		public OrderItem(FoodItem foodItem) {
			this.itemName = foodItem.getItemName();
			this.itemPrice = foodItem.getItemPrice();
		}

	}

}
