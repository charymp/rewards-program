package com.retailer.api.rest.rewardsprogram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.api.rest.rewardsprogram.bean.Customer;
import com.retailer.api.rest.rewardsprogram.service.CustomerService;

@RestController
@RequestMapping(path = "/customers")
public class CustomersController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/all")
	public List<Customer> getAllCustomers() {
		return customerService.findAll();
	}
}
