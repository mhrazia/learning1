package com.banking.consumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.consumer.entity.TransactionEntity;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Integer>{

//	@Query("SELECT balance FROM transaction where (accountId, transactiondate) = (select accountId,max(transactiondate) from transaction where accountId=:acId group by accountId)")

	
	@Query("SELECT t.balance FROM TransactionEntity t where t.transactiondate = (select max(t.transactiondate) from t where t.accountno=:accountno)")
	double getLatestBalance(@Param("accountno") int accountno);

	@Query("select t from TransactionEntity t where t.accountno=:accountno")
	List<TransactionEntity> findAllTransactionsForAccount(@Param("accountno") int accountno);
	
	@Query("select count(*) from TransactionEntity t where t.accountno=:accountno")
	int getTransactionCount(@Param("accountno") int accountno);
}
