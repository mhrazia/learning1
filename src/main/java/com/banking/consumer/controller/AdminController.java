package com.banking.consumer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.consumer.entity.CustomerEntity;
import com.banking.consumer.entity.TransactionEntity;
import com.banking.consumer.services.AdminService;
import com.banking.consumer.utility.CustomerUtility;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;
	

	@PostMapping("/registerdefault")
	public ResponseEntity<Map<String,String>> registerAdminUser(HttpServletRequest request) {

	    //int consumerId = ConsumerUtil.getConsumerId(request.getAttribute("consumerID"));
		CustomerEntity consumer = service.createDefaultAdmin();
		return CustomerUtility.retreiveTokenInfo(request, consumer);
	}
	
}
