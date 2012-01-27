package org.bura.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.bura.example.app.domain.dto.Account;
import org.bura.example.app.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	/**
	 * @throws IllegalArgumentException
	 */
	@Transactional
	public void create(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("Account is null");
		}

		Account acc = accountRepo.save(account);
		account.setId(acc.getId());
	}

	public List<Account> getAll() {
		List<Account> all = new ArrayList<Account>();
		for (Account acc : accountRepo.findAll()) {
			all.add(acc);
		}

		return all;
	}

	public Account get(Long id) {
		if (id == null || id < 1) {
			return null;
		}

		return accountRepo.findOne(id);
	}

	@Transactional
	public void remove(Long id) {
		if (id == null || id < 1) {
			return;
		}

		accountRepo.delete(id);
	}

}
