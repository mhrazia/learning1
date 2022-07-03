package com.banking.consumer.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.consumer.entity.TransactionEntity;
import com.banking.consumer.exception.TransactionException;
import com.banking.consumer.repository.TransactionRepo;

@Service
@Transactional
public class TransactionService {

	@Autowired
	TransactionRepo repo;
	
	public List<TransactionEntity> getConsumerTransactions(int accID) throws TransactionException{
		return repo.findAllTransactionsForAccount(accID);
	}

	public List<TransactionEntity> getAllTransactions() {
		return repo.findAll();
	}
	
	public boolean addTransaction(int accID, String transtype, String transamount)  throws TransactionException {
		double balance = repo.getTransactionCount(accID) > 0 ? repo.getLatestBalance(accID) : 0; 
		
		int type = (transtype.equals("CREDIT") ? 1 : -1);
		double amount = Double.parseDouble(transamount);
		if(type == -1 && balance < amount) throw new TransactionException("Insufficient balance..");
		balance += (amount * type);
		TransactionEntity entity = new TransactionEntity(accID, type,amount, balance);
		entity.setTransactionDate(System.currentTimeMillis());
		try {
			repo.save(entity);
			return true;
		}
		catch(Exception e) {
			throw new TransactionException("Transactional failure..");
		}
	}

}
