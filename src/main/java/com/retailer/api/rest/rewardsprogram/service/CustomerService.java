package com.retailer.api.rest.rewardsprogram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retailer.api.rest.rewardsprogram.bean.Customer;
import com.retailer.api.rest.rewardsprogram.jpa.CustomerSpringDataJpaRepository;

@Component
public class CustomerService {

	@Autowired
	private CustomerSpringDataJpaRepository customerRepository;

	public List<Customer> findAll() {
		System.out.println("Total Customers Count - " + customerRepository.count());
		return customerRepository.findAll();
	}
}