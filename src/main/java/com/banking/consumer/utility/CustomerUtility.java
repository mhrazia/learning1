package com.banking.consumer.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.banking.consumer.constants.ConsumerConstants;
import com.banking.consumer.entity.CustomerEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CustomerUtility {

	/**
	 * To verify the encrypted password with the given password
	 * @param source
	 * @param pwdToCompare
	 * @return
	 */
	public static boolean isPasswordMatches(String source, String pwdToCompare) {
		return BCrypt.checkpw(source, pwdToCompare);
	}
	
	/**
	 * To encrypt the password
	 * @param pwd
	 * @return
	 */
	public static String encryptPassword(String pwd) {
		String encrypted =  BCrypt.hashpw(pwd, BCrypt.gensalt(10));
		return encrypted;
	}
	
	/**
	 * To generate the JSON web token along with the consumer details
	 * @param customer
	 * @return
	 */
	public static String generateToken(HttpServletRequest request, CustomerEntity customer)
	{
		long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, ConsumerConstants.ENCRYPT_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + ConsumerConstants.TOKEN_VALIDITY))
                .claim("username", customer.getUserName())
                .claim("accountno",customer.getAccountNo())
                .claim("session", request.getSession().toString())
                .compact();
        
		return token;
	}

	/**
	 * To retrieve the consumer id stored in the authentication token
	 * @param id
	 * @return
	 */
	public static int getAccountID(Object id) {
		// TODO Auto-generated method stub
		return id != null ? Integer.parseInt(id.toString()) : -1;
	}
	
	/**
	 * 
	 * @param consumer
	 * @return
	 */
	public static ResponseEntity<Map<String, String>> retreiveTokenInfo(HttpServletRequest request,CustomerEntity consumer) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("token", CustomerUtility.generateToken(request,consumer));
        return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
