package org.bura.example.app.domain.dto;

import java.math.BigDecimal;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Account {

	@GraphId
	private Long id;

	@Indexed(indexType = IndexType.SIMPLE, indexName = "account-number-search")
	private String number;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "account-owner-search")
	private String owner;

	private BigDecimal balance;

	public Account() {
	}

	public Account(String number, String owner, BigDecimal balance) {
		this();
		this.number = number;
		this.owner = owner;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
