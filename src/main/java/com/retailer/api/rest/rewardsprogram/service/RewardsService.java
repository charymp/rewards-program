package com.retailer.api.rest.rewardsprogram.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retailer.api.rest.rewardsprogram.bean.MonthlyRewards;
import com.retailer.api.rest.rewardsprogram.bean.Transaction;
import com.retailer.api.rest.rewardsprogram.jpa.TransactionSpringDataJpaRepository;

@Component
public class RewardsService {

	@Autowired
	private TransactionSpringDataJpaRepository transactionRepository;

	public List<MonthlyRewards> findMonthWiseRewardsPerCustomerId(long id) {
		List<Transaction> customerTransactions = transactionRepository.findAllByCustomerId(id);
		List<MonthlyRewards> monthlyRewardsList = new ArrayList<MonthlyRewards>();
		List<String> monthsAdded = new ArrayList<>();

		for (Transaction tx : customerTransactions) {
			MonthlyRewards monthlyRewards = new MonthlyRewards();
			monthlyRewards.setRewards(getRewardsForTransaction(tx.getAmount()));
			monthlyRewards.setMonth(tx.getTxDate().getMonth().toString());
			monthlyRewards.setTxDate(tx.getTxDate());
			monthlyRewardsList.add(monthlyRewards);

			monthsAdded.add(monthlyRewards.getMonth());
		}
		return monthlyRewardsList;
	}

	private int getRewardsForTransaction(double amount) {
		int intAmount = (int) amount;
		int rewards = 0;

		if (intAmount > 100) {
			rewards = (intAmount - 100) * 2 + 50;
		} else if (intAmount > 50 && intAmount <= 100) {
			rewards = intAmount - 50;
		}

		return rewards;
	}

	public int getTotalYearRewards(List<MonthlyRewards> monthlyRewardsList) {
		int totalYearRewards = 0;
		for (MonthlyRewards monthlyRewards : monthlyRewardsList) {
			totalYearRewards += monthlyRewards.getRewards();
		}

		return totalYearRewards;
	}

}