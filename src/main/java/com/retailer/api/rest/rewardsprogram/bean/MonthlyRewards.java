package com.retailer.api.rest.rewardsprogram.bean;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyRewards {
	@JsonProperty(value = "Month")
	private String month;
	@JsonProperty(value = "Rewards")
	private int rewards;
	@JsonProperty(value = "TransactionDate")
	private LocalDate txDate;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	public LocalDate getTxDate() {
		return txDate;
	}

	public void setTxDate(LocalDate txDate) {
		this.txDate = txDate;
	}

	@Override
	public String toString() {
		return "MonthlyRewards [month=" + month + ", rewards=" + rewards + ", txCount=" + txDate + "]";
	}

}