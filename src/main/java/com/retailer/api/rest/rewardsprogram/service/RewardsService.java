package com.retailer.api.rest.rewardsprogram.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.retailer.api.rest.rewardsprogram.bean.Customer;
import com.retailer.api.rest.rewardsprogram.bean.MonthlyRewards;
import com.retailer.api.rest.rewardsprogram.bean.TotalRewards;
import com.retailer.api.rest.rewardsprogram.bean.Transaction;
import com.retailer.api.rest.rewardsprogram.jpa.TransactionSpringDataJpaRepository;

@Component
public class RewardsService {
	private Logger LOG = LoggerFactory.getLogger(RewardsService.class);
	private final String AUTH_TOKEN_KEY = "spring.basic.authorization.token";

	@Autowired
	private TransactionSpringDataJpaRepository transactionRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Environment env;

	public List<TotalRewards> findRewardsForLastXYearsForAllCustomers(int last_x_years) {

		List<TotalRewards> totalRewardsForAllCustomers = new ArrayList<>();

		for (Customer customer : customerService.findAll()) {
			totalRewardsForAllCustomers.add(getTotalRewardsPerCustomer(customer.getId(),
					findRewardsForLastXYearsPerCustomer(last_x_years, customer.getId())));
		}

		return totalRewardsForAllCustomers;
	}

	public List<MonthlyRewards> findRewardsForLastXYearsPerCustomer(int last_x_years, long id) {
		return getMonthlyRewardsListGivenTxs(transactionRepository.findAllByCustomerIdAndTxDateBetween(id,
				LocalDate.now().minusYears(last_x_years), LocalDate.now()));
	}

	public List<MonthlyRewards> findMonthWiseRewardsPerCustomerId(long id) {
		return getMonthlyRewardsListGivenTxs(transactionRepository.findAllByCustomerId(id));
	}

	private List<MonthlyRewards> getMonthlyRewardsListGivenTxs(List<Transaction> customerTransactions) {
		LOG.debug("getMonthlyRewardsListGivenTxs: Txs Size - {}", customerTransactions.size());
		List<MonthlyRewards> monthlyRewardsList = new ArrayList<MonthlyRewards>();

		for (Transaction tx : customerTransactions) {
			MonthlyRewards monthlyRewards = new MonthlyRewards();
			monthlyRewards.setRewards(getRewardsForTransaction(tx.getAmount()));
			monthlyRewards.setMonth(tx.getTxDate().getMonth().toString());
			monthlyRewards.setTxDate(tx.getTxDate());
			monthlyRewardsList.add(monthlyRewards);
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

	public List<TotalRewards> getTotalRewardsForAllCustomers() {
		List<TotalRewards> totalRewardsForAllCustomers = new ArrayList<>();

		for (Customer customer : customerService.findAll()) {
			totalRewardsForAllCustomers.add(getTotalRewardsPerCustomer(customer.getId(),
					getMonthlyRewardsListGivenTxs(transactionRepository.findAllByCustomerId(customer.getId()))));
		}

		return totalRewardsForAllCustomers;
	}

	public TotalRewards getTotalRewardsPerCustomer(long id) {
		TotalRewards totalRewards = new TotalRewards();
		List<MonthlyRewards> monthlyRewardsList = findMonthWiseRewardsPerCustomerId(id);

		totalRewards.setCustomerId(id);
		totalRewards.setMonthlyRewardsList(monthlyRewardsList);
		totalRewards.setTotalRewards(getTotalYearRewardsPoints(monthlyRewardsList));
		return totalRewards;
	}

	public TotalRewards getTotalRewardsPerCustomer(Long customerId, List<MonthlyRewards> monthlyRewardsList) {
		int totalYearRewards = 0;
		for (MonthlyRewards monthlyRewards : monthlyRewardsList) {
			totalYearRewards += monthlyRewards.getRewards();
		}

		TotalRewards totalRewards = new TotalRewards();

		totalRewards.setCustomerId(customerId);
		totalRewards.setMonthlyRewardsList(monthlyRewardsList);
		totalRewards.setTotalRewards(totalYearRewards);

		return totalRewards;
	}

	public int getTotalYearRewardsPoints(List<MonthlyRewards> monthlyRewardsList) {
		int totalYearRewards = 0;
		for (MonthlyRewards monthlyRewards : monthlyRewardsList) {
			totalYearRewards += monthlyRewards.getRewards();
		}

		return totalYearRewards;
	}

	public boolean isAuthorizationTokenNotValid(String authorization) {
		if (!StringUtils.hasLength(authorization) || !authorization.equals(env.getProperty(AUTH_TOKEN_KEY))) {
			return true;
		}
		return false;
	}

}