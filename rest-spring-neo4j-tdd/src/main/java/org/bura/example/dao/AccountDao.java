package org.bura.example.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bura.example.dto.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

	private List<Account> accounts = new ArrayList<Account>();

	private long nextId = 1;

	public AccountDao() {
		Account acc = new Account("1111", new BigDecimal(0));
		acc.setId(nextId++);
		accounts.add(acc);
		acc = new Account("2222", new BigDecimal(100));
		acc.setId(nextId++);
		accounts.add(acc);
	}

	public void remove(long id) {
		Account acc = findById(id);
		if (acc == null) {
			throw new IllegalArgumentException();
		}

		accounts.remove(acc);
	}

	public void createAccount(Account account) {
		if (account == null) {
			throw new IllegalArgumentException();
		}

		long id = nextId++;
		account.setId(id);
		accounts.add(account);
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

	public Account getAccount(long id) {
		return findById(id);
	}

	private Account findById(long id) {
		for (Account acc : accounts) {
			if (id == acc.getId()) {
				return acc;
			}
		}

		return null;
	}

}
