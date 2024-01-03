package com.bootcat.BootcatBank.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component

@Entity
@Table(name = "TransactionHistory")
public class TransactionHistory {

	@Id
	private int id;
	private int account_number;
	private String account_holder;
	private int amount;
	public static int total;

	private String transactionType;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionHistory() {
	}

	public TransactionHistory(int id, int account_number, String account_holder, int amount, String transactionType) {
		super();
		this.id = id;
		this.account_number = account_number;
		this.account_holder = account_holder;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getAccount_holder() {
		return account_holder;
	}

	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static int getTotal() {
		return total;
	}

	public static void setTotal(int total) {
		TransactionHistory.total = total;
	}

}
