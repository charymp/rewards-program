package com.retailer.api.rest.rewardsprogram.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.api.rest.rewardsprogram.bean.Transaction;

public interface TransactionSpringDataJpaRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findAllByCustomerId(Long id);

	List<Transaction> findAllByTxDateBetween(LocalDate from, LocalDate to);

	List<Transaction> findAllByCustomerIdAndTxDateBetween(Long id, LocalDate from, LocalDate to);
}
