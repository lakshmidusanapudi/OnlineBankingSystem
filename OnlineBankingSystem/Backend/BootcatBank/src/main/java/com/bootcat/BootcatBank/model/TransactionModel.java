package com.bootcat.BootcatBank.model;

import org.springframework.stereotype.Component;

@Component
public class TransactionModel {
	private int id;
	private int balance;
	private String transactionType;

	public String getTransactionType() {
		return transactionType;
	}

	public TransactionModel() {

	}

	public TransactionModel(int id, int balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
