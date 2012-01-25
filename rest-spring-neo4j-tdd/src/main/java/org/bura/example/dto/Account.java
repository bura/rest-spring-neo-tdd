package org.bura.example.dto;

import java.math.BigDecimal;

public class Account {

	private long id;

	private String number;

	private BigDecimal balance;

	public Account() {
	}

	public Account(String number, BigDecimal balance) {
		this();
		this.number = number;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
