package com.banking.consumer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.consumer.entity.CustomerEntity;
import com.banking.consumer.entity.TransactionEntity;
import com.banking.consumer.exception.ConsumerException;
import com.banking.consumer.repository.AdminRepo;
import com.banking.consumer.utility.CustomerUtility;

@Service
public class AdminService {
	@Autowired
	AdminRepo repo;
	
	public CustomerEntity createDefaultAdmin() {
		try {
			if(repo.count() == 0) { // no user created, lets create an admin user
				CustomerEntity adminEntity = new CustomerEntity();
				adminEntity.setFirstName("administrator");
				adminEntity.setLastName("a");
				adminEntity.setUserName("admin");
				adminEntity.setPassword(CustomerUtility.encryptPassword("adminpass"));
				adminEntity.setUserrole("ADMIN");
				adminEntity = repo.save(adminEntity);
				return adminEntity;
			}
			else {
				throw new ConsumerException("Default admin is already existing");
			}
		}catch(Exception e) {
			throw new ConsumerException("Failed to create default admin user");
		}
	}
}
