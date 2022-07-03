package com.banking.consumer.services;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.consumer.entity.CustomerEntity;
import com.banking.consumer.entity.TransactionEntity;
import com.banking.consumer.exception.ConsumerException;
import com.banking.consumer.repository.CustomerRepo;
import com.banking.consumer.utility.CustomerUtility;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepo repo;
	
	public CustomerService() {		
	}
	
	public CustomerEntity registerUser(String username, String password, String firstname, String lastname) {
		if(repo.getConsumerCountByUserName(username) >0 ) {
			throw new ConsumerException("User already existing");
		}
		CustomerEntity entity = new CustomerEntity(username, password, firstname, lastname);
		try {
			entity.setAccountNo(repo.getLatestAccountNo());
			return repo.save(entity);
		}catch(Exception e) {
			System.out.println(e);
			throw new ConsumerException("User registration failed...");
		}
	}

	public CustomerEntity login(String username, String pwd) {
		CustomerEntity entity = repo.getConsumerByUsername(username);
		if(!CustomerUtility.isPasswordMatches(pwd, entity.getPassword())) {
			throw new ConsumerException("Invalid user credentials!!");
		}
		return entity;
	}

	public Boolean checkUserAvailability(String username) throws ConsumerException {
		if(repo.getConsumerCountByUserName(username) > 0) {
			throw new ConsumerException("Username is already registered!!");
		}
		return true;
	}
	
}