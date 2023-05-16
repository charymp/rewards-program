package com.retailer.api.rest.rewardsprogram.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.api.rest.rewardsprogram.bean.Customer;
import com.retailer.api.rest.rewardsprogram.bean.MonthlyRewards;
import com.retailer.api.rest.rewardsprogram.bean.TotalRewards;
import com.retailer.api.rest.rewardsprogram.service.RewardsService;

@RestController
@RequestMapping(path = "/rewards")
public class RewardsController {

	private final String AUTH_TOKEN_KEY = "spring.basic.authorization.token";

	@Autowired
	private RewardsService rewardsService;

	@Autowired
	private CustomersController customerController;

	@Autowired
	private Environment env;

	@GetMapping(path = "/customers/{id}")
	public ResponseEntity<Object> getTotalRewardsPerCustomer(@RequestParam(name = "Authorization") String authorization,
			@PathVariable long id) {

		if (!StringUtils.hasLength(authorization) || !authorization.equals(env.getProperty(AUTH_TOKEN_KEY))) {
			return new ResponseEntity<Object>(String.format("AuthenticationFailed. Verify Authorization Header"),
					HttpStatus.UNAUTHORIZED);
		}

		TotalRewards totalRewards = getTotalRewards(id);
		return new ResponseEntity<Object>(totalRewards, HttpStatus.OK);
	}

	@GetMapping(path = "/customers")
	public ResponseEntity<Object> getTotalRewardsForAllCustomers() {
		List<Customer> customers = customerController.getAllCustomers();
		List<TotalRewards> totalRewardsForAllCustomers = new ArrayList<TotalRewards>();

		for (Customer customer : customers) {
			totalRewardsForAllCustomers.add(getTotalRewards(customer.getId()));
		}
		return new ResponseEntity<Object>(totalRewardsForAllCustomers, HttpStatus.OK);
	}

	private TotalRewards getTotalRewards(long id) {
		TotalRewards totalRewards = new TotalRewards();
		List<MonthlyRewards> monthlyRewardsList = rewardsService.findMonthWiseRewardsPerCustomerId(id);

		totalRewards.setCustomerId(id);
		totalRewards.setMonthlyRewardsList(monthlyRewardsList);
		totalRewards.setTotalRewards(rewardsService.getTotalYearRewards(monthlyRewardsList));
		return totalRewards;
	}
}