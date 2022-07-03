package com.banking.consumer.constants;

public class ConsumerConstants {

	public static final int TRANS_CREDIT = 1;
	public static final int TRANS_DEBIT = -1;
	public static final String ENCRYPT_KEY = "ENCRYPT_CONSUMER_AUTHENTICITY";
	public static final int TOKEN_VALIDITY =  1 * 60* 60 * 1000; //1 hour
	
	public enum ROLES {
		ADMIN, USER
	}
}
