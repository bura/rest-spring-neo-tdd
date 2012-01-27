package org.bura.example.tests.dao;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.bura.example.dao.AccountDao;
import org.bura.example.dto.Account;
import org.bura.example.spring.app.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = AppConfig.class)
public class AccountDaoTest {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private Neo4jTemplate neo4j;

	@Test
	@Transactional
	public void testCreateAccount() {
		Account acc = new Account("4321", "toto toto", new BigDecimal(10000));
		accountDao.createAccount(acc);

		Assert.assertTrue(acc.getId() > 0L);
		GraphRepository<Account> repo = getAccountRepo();
		Account nacc = repo.findOne(acc.getId());
		Assert.assertEquals(nacc.getId(), acc.getId());
		Assert.assertEquals(nacc.getNumber(), acc.getNumber());
		Assert.assertEquals(nacc.getOwner(), acc.getOwner());
		Assert.assertEquals(nacc.getBalance(), acc.getBalance());

		try {
			accountDao.createAccount(null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	@Transactional
	public void testGetAccounts() {
		GraphRepository<Account> repo = getAccountRepo();
		repo.save(new Account());
		repo.save(new Account());

		List<Account> accs = accountDao.getAccounts();
		Assert.assertNotNull(accs);
		Assert.assertTrue(accs.size() == 2);
		for (Account acc : accs) {
			Assert.assertNotNull(acc);
		}
	}

	@Test
	@Transactional
	public void testGetAccount() {
		GraphRepository<Account> repo = getAccountRepo();
		Account tacc = new Account("111", "Иванов И.И.", new BigDecimal(1000));
		tacc = repo.save(tacc);

		Account acc = accountDao.getAccount(tacc.getId());

		Assert.assertNotNull(acc);
		Assert.assertEquals(tacc.getId(), acc.getId());
		Assert.assertEquals(tacc.getNumber(), acc.getNumber());
		Assert.assertEquals(tacc.getOwner(), acc.getOwner());
		Assert.assertEquals(tacc.getBalance(), acc.getBalance());

		acc = accountDao.getAccount(-1L);
		Assert.assertNull(acc);
	}

	@Test
	public void testRemove() {
		GraphRepository<Account> repo = getAccountRepo();
		Account tacc = new Account("111", "Иванов И.И.", new BigDecimal(1000));
		Transaction tr = neo4j.beginTx();
		try {
			tacc = repo.save(tacc);
			tr.success();
		} finally {
			tr.finish();
		}

		tr = neo4j.beginTx();
		try {
			accountDao.remove(tacc.getId());
			tr.success();
		} finally {
			tr.finish();
		}

		Account acc = repo.findOne(tacc.getId());
		Assert.assertNull(acc);

		try {
			accountDao.remove(-1L);
			Assert.fail();
		} catch (IllegalArgumentException e) {
		}
	}

	private GraphRepository<Account> getAccountRepo() {
		return neo4j.repositoryFor(Account.class);
	}

}
