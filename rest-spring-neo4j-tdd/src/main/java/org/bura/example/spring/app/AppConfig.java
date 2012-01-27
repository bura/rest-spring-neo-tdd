package org.bura.example.spring.app;

import org.bura.example.dao.AccountDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/neo4j.xml")
@ComponentScan(basePackages = { "org.bura.example.dao", "org.bura.example.dto",
		"org.bura.example.controller" })
public class AppConfig {

	public AppConfig() {
		super();
	}

	@Bean
	public AccountDao getAccountDao() {
		return new AccountDao();
	}

}