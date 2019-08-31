package com.customer.order.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import com.customer.order.enums.OrderStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Used for Order Details")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	private Customer customer;

	@ApiModelProperty(notes = "food ordered date")
	private LocalDateTime orderDate;
	private LocalDateTime lastModified = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foodItemId")
	private FoodItem foodItem;

	private Integer foodItemCount;
	private boolean isActive = true;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name = "items")
	@Type(type = "com.customer.order.entity.OrderedItems$OrderedItemsType")
	private OrderedItems orderedItems = new OrderedItems();

	public OrderDetail(Customer customer, Restaurant restaurant, FoodItem foodItem) {
		this.customer = customer;
		this.restaurant = restaurant;
		this.foodItem = foodItem;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", customer=" + customer + ", restaurant=" + restaurant + ", foodItem="
				+ foodItem + ", orderStatus=" + orderStatus + "]";
	}
}
