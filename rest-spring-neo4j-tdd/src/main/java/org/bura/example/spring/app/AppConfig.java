package org.bura.example.spring.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/neo4j.xml")
@ComponentScan(basePackages = "org.bura.example.app")
public class AppConfig {

	public AppConfig() {
		super();
	}

}