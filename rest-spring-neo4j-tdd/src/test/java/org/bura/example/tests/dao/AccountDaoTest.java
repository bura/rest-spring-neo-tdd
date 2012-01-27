package org.bura.example.tests.dao;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.bura.example.app.domain.dto.Account;
import org.bura.example.app.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class AccountDaoTest {

	@Test
	@Transactional
	public void testCreateAccount() {
		Account acc = new Account("4321", "toto toto", new BigDecimal(10000));
		accountService.create(acc);

		Assert.assertTrue(acc.getId() > 0L);
		GraphRepository<Account> repo = getAccountRepo();
		Account nacc = repo.findOne(acc.getId());
		Assert.assertEquals(nacc.getId(), acc.getId());
		Assert.assertEquals(nacc.getNumber(), acc.getNumber());
		Assert.assertEquals(nacc.getOwner(), acc.getOwner());
		Assert.assertEquals(nacc.getBalance(), acc.getBalance());

		try {
			accountService.create(null);
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

		List<Account> accs = accountService.getAll();
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

		Account acc = accountService.get(tacc.getId());

		Assert.assertNotNull(acc);
		Assert.assertEquals(tacc.getId(), acc.getId());
		Assert.assertEquals(tacc.getNumber(), acc.getNumber());
		Assert.assertEquals(tacc.getOwner(), acc.getOwner());
		Assert.assertEquals(tacc.getBalance(), acc.getBalance());

		acc = accountService.get(-1L);
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
			accountService.remove(tacc.getId());
			tr.success();
		} finally {
			tr.finish();
		}

		Account acc = repo.findOne(tacc.getId());
		Assert.assertNull(acc);
	}

	@Autowired
	private AccountService accountService;

	@Autowired
	private Neo4jTemplate neo4j;

	private GraphRepository<Account> getAccountRepo() {
		return neo4j.repositoryFor(Account.class);
	}

	@Configuration
	@ImportResource("classpath:/spring/test-neo4j.xml")
	@ComponentScan(basePackages = "org.bura.example.app")
	static class ContextConfiguration {

		public ContextConfiguration() {
			super();
		}

	}

}
