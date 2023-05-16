package com.retailer.api.rest.rewardsprogram.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailer.api.rest.rewardsprogram.bean.Customer;

public interface CustomerSpringDataJpaRepository extends JpaRepository<Customer, Long> {

}
