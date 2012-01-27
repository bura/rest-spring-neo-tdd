package org.bura.example.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bura.example.dto.Account;
import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

	@Autowired
	private Neo4jTemplate neo4j;

	private GraphRepository<Account> repo;

	@PostConstruct
	void init() {
		repo = neo4j.repositoryFor(Account.class);
	}

	public void remove(Long id) {
		Account acc = findById(id);
		if (acc == null) {
			throw new IllegalArgumentException();
		}

		repo.delete(acc);
	}

	public void createAccount(Account account) {
		if (account == null) {
			throw new IllegalArgumentException();
		}

		Account acc = repo.save(account);
		account.setId(acc.getId());
	}

	public List<Account> getAccounts() {
		ClosableIterable<Account> itHolder = repo.findAll();
		try {
			List<Account> accs = new ArrayList<Account>();
			Iterator<Account> iter = itHolder.iterator();
			while (iter.hasNext()) {
				accs.add(iter.next());
			}

			return accs;
		} finally {
			itHolder.close();
		}
	}

	public Account getAccount(Long id) {
		return findById(id);
	}

	private Account findById(Long id) {
		if (id == null || id < 1) {
			return null;
		}

		return repo.findOne(id);
	}

}
