package com.banking.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.consumer.entity.CustomerEntity;

@Repository
public interface AdminRepo extends JpaRepository<CustomerEntity, Integer> {
	
}
