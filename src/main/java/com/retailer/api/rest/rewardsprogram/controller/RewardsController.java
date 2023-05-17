package com.retailer.api.rest.rewardsprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.api.rest.rewardsprogram.service.RewardsService;

@RestController
@RequestMapping(path = "/rewards")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@GetMapping(path = "/per-customer/{last_x_years}/{customer_id}")
	public ResponseEntity<Object> getRewardsForLastXYearsPerCustomer(@PathVariable int last_x_years,
			@PathVariable long customer_id) {

		return new ResponseEntity<Object>(rewardsService.getTotalRewardsPerCustomer(customer_id,
				rewardsService.findRewardsForLastXYearsPerCustomer(last_x_years, customer_id)), HttpStatus.OK);
	}

	@GetMapping(path = "/for-all-customers/{last_x_years}")
	public ResponseEntity<Object> getRewardsForLastXYearsForAllCustomers(@PathVariable int last_x_years) {

		return new ResponseEntity<Object>(rewardsService.findRewardsForLastXYearsForAllCustomers(last_x_years),
				HttpStatus.OK);
	}

	@GetMapping(path = "/per-customer/{id}")
	public ResponseEntity<Object> getTotalRewardsPerCustomer(@PathVariable long id) {

		return new ResponseEntity<Object>(rewardsService.getTotalRewardsPerCustomer(id), HttpStatus.OK);
	}

	@GetMapping(path = "/for-all-customers")
	public ResponseEntity<Object> getTotalRewardsForAllCustomers() {
		return new ResponseEntity<Object>(rewardsService.getTotalRewardsForAllCustomers(), HttpStatus.OK);
	}

}