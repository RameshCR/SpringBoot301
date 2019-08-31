package com.customer.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.customer.order.enums.CuisineType;
import com.customer.order.enums.FoodType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = " Used for Food Item Entity ")
public class FoodItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private FoodType foodType;
	@Enumerated(EnumType.STRING)
	private CuisineType cuisineType;

	@ApiModelProperty(notes = "food item name")
	private String itemName;
	private BigDecimal itemPrice;
	private boolean isActive = true;

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
		FoodItem other = (FoodItem) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", foodType=" + foodType + ", cuisineType=" + cuisineType + ", itemName="
				+ itemName + ", itemPrice=" + itemPrice + "]";
	}

}
