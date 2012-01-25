package org.bura.example.tests.dao;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.bura.example.dao.AccountDao;
import org.bura.example.dto.Account;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountDaoTest {

	private static AccountDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AccountDao();
	}

	@Test
	public void testGetAccounts() {
		List<Account> accs = dao.getAccounts();

		Assert.assertNotNull(accs);
		Assert.assertTrue(accs.size() > 0);
		for (Account acc : accs) {
			Assert.assertNotNull(acc);
		}
	}

	@Test
	public void testGetAccount() {
		Account acc = dao.getAccount(1L);

		Assert.assertNotNull(acc);
		Assert.assertEquals(1L, acc.getId());

		acc = dao.getAccount(-1L);
		Assert.assertNull(acc);
	}

	@Test
	public void testCreateAccount() {
		Account acc = new Account("4321", new BigDecimal(10000));
		dao.createAccount(acc);

		Assert.assertTrue(acc.getId() > 0L);
		Account nacc = dao.getAccount(acc.getId());
		Assert.assertEquals(nacc.getId(), acc.getId());
		Assert.assertEquals(nacc.getNumber(), acc.getNumber());
		Assert.assertEquals(nacc.getBalance(), acc.getBalance());

		try {
			dao.createAccount(null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testRemove() {
		dao.remove(1L);

		Account acc = dao.getAccount(1L);
		Assert.assertNull(acc);

		acc = dao.getAccount(-1L);
		Assert.assertNull(acc);
	}

}
