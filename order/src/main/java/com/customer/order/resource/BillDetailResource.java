package com.customer.order.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.customer.order.entity.OrderDetail;
import com.customer.order.entity.OrderedItems;
import com.customer.order.entity.OrderedItems.OrderItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class BillDetailResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerName;
	private Integer orderNo;
	private LocalDateTime orderDate;
	private OrderedItems items;
	private BigDecimal totalBill;

	public BillDetailResource(OrderDetail orderDetail) {
		this.customerName = orderDetail.getCustomer().getFirstName();
		this.orderNo = orderDetail.getId();
		this.orderDate = orderDetail.getOrderDate();
		this.items = orderDetail.getOrderedItems();
		this.totalBill = orderDetail.getOrderedItems().stream().map(OrderItem::getItemPrice).reduce(BigDecimal::add)
				.get();
	}
}
