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

import com.retailer.api.rest.rewardsprogram.bean.Transaction;
import com.retailer.api.rest.rewardsprogram.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "rewards/transactions")
public class TransactionController {// TransactionController
	@Autowired
	private TransactionService txService;

	@GetMapping(path = "/all")
	public List<Transaction> getAllTrasactions() {
		return txService.findAllTxs();
	}

	@PostMapping(path = "/create")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody Transaction tx) {
		txService.saveTransaction(tx);
		return new ResponseEntity<Object>(null, HttpStatus.CREATED);
	}
}
