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

import com.banking.consumer.entity.CustomerEntity;
import com.banking.consumer.entity.TransactionEntity;
import com.banking.consumer.services.CustomerService;
import com.banking.consumer.utility.CustomerUtility;

@RestController
@RequestMapping("/user")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(HttpServletRequest request, @RequestBody Map<String,String> customerDetails) {
		CustomerEntity consumer = service.registerUser(customerDetails.get("username"), CustomerUtility.encryptPassword(customerDetails.get("password")), customerDetails.get("firstname"), customerDetails.get("lastname"));
		return CustomerUtility.retreiveTokenInfo(request, consumer);
	}

	@GetMapping("/login")
	public ResponseEntity<Map<String,String>> login(HttpServletRequest request, @RequestBody Map<String,String> loginDetails) {

	    //int consumerId = ConsumerUtil.getConsumerId(request.getAttribute("consumerID"));
		CustomerEntity consumer = service.login(loginDetails.get("username"),loginDetails.get("password"));
		return CustomerUtility.retreiveTokenInfo(request, consumer);
	}
	
	@GetMapping("/checkavailability")
	public ResponseEntity<Boolean> checkUserAvailability(HttpServletRequest request, @RequestBody Map<String,String> userDetails) {
		return new ResponseEntity<Boolean>(service.checkUserAvailability(userDetails.get("username")),HttpStatus.OK);
	}
	
}
