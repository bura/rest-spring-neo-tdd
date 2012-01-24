package org.bura.example.tests.controller;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class AccountControllerTest {

	@Test
	public void testCreateAccount() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testGetAccounts() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testGetAccount() {
		Assert.fail("Not implemented");
	}

	@Test
	public void testRemoveAccount() {
		Assert.fail("Not implemented");
	}
	
	@BeforeClass
	public static void init() {

	}

	@AfterClass
	public static void destroy() {

	}

	@Configuration
	static class ContextConfiguration {

		public ContextConfiguration() {
			super();
		}

	}

}
