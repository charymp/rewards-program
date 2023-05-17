package com.retailer.api.rest.rewardsprogram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.api.rest.rewardsprogram.bean.Customer;
import com.retailer.api.rest.rewardsprogram.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rewards/customers")
public class CustomersController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/all")
	public List<Customer> getAllCustomers() {
		return customerService.findAll();
	}

	@PostMapping(path = "/create")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return new ResponseEntity<Object>(null, HttpStatus.CREATED);
	}
}
