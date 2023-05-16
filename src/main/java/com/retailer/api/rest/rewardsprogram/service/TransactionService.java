package com.retailer.api.rest.rewardsprogram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retailer.api.rest.rewardsprogram.bean.Transaction;
import com.retailer.api.rest.rewardsprogram.jpa.TransactionSpringDataJpaRepository;

@Component
public class TransactionService {

	@Autowired
	private TransactionSpringDataJpaRepository transactionRepository;

	public List<Transaction> findByCustomerId(long id) {
		return transactionRepository.findAllByCustomerId(id);
	}

}