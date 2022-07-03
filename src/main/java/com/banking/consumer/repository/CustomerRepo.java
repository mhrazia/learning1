package com.banking.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.consumer.entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
	@Query("SELECT c from CustomerEntity c where username=:username")
	public CustomerEntity getConsumerByUsername(@Param("username") String username);

	@Query("select count(*) from CustomerEntity c where c.username=:username")
	public int getConsumerCountByUserName(@Param("username") String username);
	
	@Query("select max(c.accountno)+1 from CustomerEntity c")
	public int getLatestAccountNo();
	
}
