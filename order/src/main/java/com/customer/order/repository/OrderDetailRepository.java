package com.customer.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.order.entity.Customer;
import com.customer.order.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

	List<OrderDetail> findByCustomerAndIsActiveTrue(Customer customer);

	@Query(" SELECT od FROM OrderDetail od WHERE od.customer.id = :customerId AND od.id = :orderId AND od.isActive = TRUE")
	OrderDetail findActiveOrderByCustomerIdAndOrderId(@Param("customerId") Integer customerId,
			@Param("orderId") Integer orderId);
}
