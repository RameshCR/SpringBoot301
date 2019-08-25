package com.customer.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.order.entity.Customer;
import com.customer.order.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	@Autowired CustomerRepository customerRepository;

	@Override
	public Customer getCustomer(Integer customerId) {
		return Optional.ofNullable(customerRepository.findById(customerId)).get()
				.orElse(null);
	}

}
