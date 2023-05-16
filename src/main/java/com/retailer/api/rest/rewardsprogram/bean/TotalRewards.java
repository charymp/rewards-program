package com.retailer.api.rest.rewardsprogram.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalRewards {
	@JsonProperty(value = "CustomerId")
	private long customerId;
	@JsonProperty(value = "Month_Wise_Rewards_List")
	private List<MonthlyRewards> monthlyRewardsList;
	@JsonProperty(value = "TotalRewards")
	private int totalRewards;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<MonthlyRewards> getMonthlyRewardsList() {
		return monthlyRewardsList;
	}

	public void setMonthlyRewardsList(List<MonthlyRewards> monthlyRewardsList) {
		this.monthlyRewardsList = monthlyRewardsList;
	}

	public int getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(int totalRewards) {
		this.totalRewards = totalRewards;
	}

	@Override
	public String toString() {
		return "TotalRewards [customerId=" + customerId + ", monthlyRewardsList=" + monthlyRewardsList
				+ ", totalRewards=" + totalRewards + "]";
	}

}