package org.bura.example.tests.controller;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.bura.example.app.domain.dto.Account;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class AccountControllerIT {

	@Test
	public void testCreateAccount() {
		Account acc = new Account("321", "test test", new BigDecimal(1000));
		Long id = rest.postForObject(BASE_URL + "/create", acc, Long.class);

		Assert.assertTrue(id > 0);

		ResponseEntity<Account> response = rest.getForEntity(
				BASE_URL + "/{id}", Account.class, id);
		Account nacc = response.getBody();
		Assert.assertNotNull(nacc);
		Assert.assertEquals(id, nacc.getId());
		Assert.assertEquals(acc.getNumber(), nacc.getNumber());
		Assert.assertEquals(acc.getOwner(), nacc.getOwner());
		Assert.assertEquals(acc.getBalance(), nacc.getBalance());
	}

	@Test
	public void testGetAccounts() {
		ResponseEntity<Account[]> response = rest.getForEntity(BASE_URL,
				Account[].class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetAccount() {
		ResponseEntity<Account> response = rest.getForEntity(
				BASE_URL + "/{id}", Account.class, 1L);
		Account account = response.getBody();

		Assert.assertNotNull(account);
		Assert.assertEquals(Long.valueOf(1L), account.getId());
	}

	@Test
	public void testRemoveAccount() {
		rest.postForEntity(BASE_URL + "/remove/{id}", null, Object.class, 1L);

		ResponseEntity<Account> response = rest.getForEntity(
				BASE_URL + "/{id}", Account.class, 1L);
		Account acc = response.getBody();
		Assert.assertNull(acc);
	}

	private static final String BASE_URL = "http://localhost:8080/api/account";

	@Autowired
	private RestTemplate rest;

	@Configuration
	static class ContextConfiguration {

		public ContextConfiguration() {
			super();
		}

		@Bean
		public RestTemplate getRest() {
			RestTemplate rest = new RestTemplate();
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(new MappingJacksonHttpMessageConverter());
			rest.setMessageConverters(list);

			return rest;
		}

	}

	private static Tomcat tomcat;

	private static int port = 8080;

	@BeforeClass
	public static void init() throws ServletException, URISyntaxException,
			LifecycleException {

		tomcat = new Tomcat();
		tomcat.setPort(port);
		tomcat.setBaseDir("target");

		StandardServer server = (StandardServer) tomcat.getServer();
		AprLifecycleListener listener = new AprLifecycleListener();
		server.addLifecycleListener(listener);

		tomcat.addWebapp("/", "rest-spring-neo4j-tdd");
		tomcat.start();
	}

	@AfterClass
	public static void destroy() throws LifecycleException {
		tomcat.stop();
	}

}
