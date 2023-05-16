package com.retailer.api.rest.rewardsprogram.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.api.rest.rewardsprogram.bean.Transaction;

public interface TransactionSpringDataJpaRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findAllByCustomerId(Long id);
}
