package org.bura.example.tests.controller;

import java.net.URISyntaxException;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
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
public class AccountControllerIT {

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

	@Configuration
	static class ContextConfiguration {

		public ContextConfiguration() {
			super();
		}

	}

}
