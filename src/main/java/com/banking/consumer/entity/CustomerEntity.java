package com.banking.consumer.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custid;

	@Column(nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int accountno;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false)
	private String userrole = "USER";

	@OneToMany(targetEntity = TransactionEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name="accountno", referencedColumnName = "accountno")
	private List<TransactionEntity> transactionList;

	
	 public CustomerEntity() {
		 super();
	 }
	
	

	/**
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 */
	public CustomerEntity(String username, String password, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}



	/**
	 * @return the custid
	 */
	public int getCustId() {
		return custid;
	}

	/**
	 * @param custid the custid to set
	 */
	public void setCustId(int custid) {
		this.custid = custid;
	}

	/**
	 * @return the accountno
	 */
	public int getAccountNo() {
		return accountno;
	}

	/**
	 * @param accountno the accountno to set
	 */
	public void setAccountNo(int accountno) {
		this.accountno = accountno;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstName() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastName() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the transactionList
	 */
	public List<TransactionEntity> getTransactionList() {
		return transactionList;
	}

	/**
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(List<TransactionEntity> transactionList) {
		this.transactionList = transactionList;
	}



	/**
	 * @return the username
	 */
	public String getUserName() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUserName(String username) {
		this.username = username;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the userrole
	 */
	public String getUserrole() {
		return userrole;
	}



	/**
	 * @param userrole the userrole to set
	 */
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	
	

}
