package com.banking.consumer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.consumer.entity.TransactionEntity;
import com.banking.consumer.services.TransactionService;
import com.banking.consumer.utility.CustomerUtility;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService service;
	
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addTransaction(HttpServletRequest request, @RequestBody Map<String,String> transDetails)
	{
		int accID = CustomerUtility.getAccountID(request.getAttribute("accountno"));
		boolean success  = service.addTransaction(accID, transDetails.get("type"), transDetails.get("amount"));
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}
	
	@GetMapping("/report/user")
	public ResponseEntity<List<TransactionEntity>> getConsumerTransactions(HttpServletRequest request)
	{
		int accID = CustomerUtility.getAccountID(request.getAttribute("accountno"));
		List<TransactionEntity> transList = service.getConsumerTransactions(accID);
		return new ResponseEntity<List<TransactionEntity>>(transList, HttpStatus.OK);
		
	}
	
	@GetMapping("/report/all")
	public ResponseEntity<List<TransactionEntity>> getAllTransactions(HttpServletRequest request)
	{
		List<TransactionEntity> transList = service.getAllTransactions();
		return new ResponseEntity<List<TransactionEntity>>(transList, HttpStatus.OK);
	}
	
}
