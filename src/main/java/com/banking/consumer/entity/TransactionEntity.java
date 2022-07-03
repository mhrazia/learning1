package com.banking.consumer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_transactions")
public class TransactionEntity implements Serializable{

//	@Column(name="trans_id", unique=true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int transactionId;

//	@Column(name="t_actId")
	private int accountno;
	private int transactionType;
	@Column(name = "transactionamount", nullable = false, precision = 2)
	private double transactionamount;
	@Column(name = "balance", nullable = false, precision = 2)
	private double balance;
//	@Column(name = "bal_after", nullable=false, precision=2)
//	private double balanceAfter;

	private long transactiondate;

	public TransactionEntity() {
		super();
	}

	/**
	 * @param accountId
	 * @param transactionAmount
	 * @param balance
	 */
	public TransactionEntity(int accountId, int transType, double transactionAmount, double balance) {
		super();
		this.accountno = accountId;
		this.transactionamount = transactionAmount;
		this.balance = balance;
		this.transactionType = transType;
	}

	/**
	 * @return the accountId
	 */
	public int getAccountNo() {
		return accountno;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountNo(int accountId) {
		this.accountno = accountId;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the transactionID
	 */
	public int getTransactionID() {
		return transactionId;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(int transactionID) {
		this.transactionId = transactionID;
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionamount;
	}

	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionamount = transactionAmount;
	}

	/**
	 * @return the transactionDate
	 */
	public long getTransactionDate() {
		return transactiondate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(long transactionDate) {
		this.transactiondate = transactionDate;
	}

	/**
	 * @return the transactionType
	 */
	public int getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

}
