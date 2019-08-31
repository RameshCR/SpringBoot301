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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Used for Restaurant Entity")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(notes = " restaurant name")
	private String name;
	private String location;
	private Integer distance;
	private BigDecimal budget;
	private BigDecimal rating;
	@Enumerated(EnumType.STRING)
	private CuisineType cuisineType;
	private String contactNo;
	private boolean isActive = true;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getName()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Override
	public String toString() {
		return "Restuarant [id=" + id + ", name=" + name + ", location=" + location + ", rating=" + rating
				+ ", cuisineType=" + cuisineType + "]";
	}

}
