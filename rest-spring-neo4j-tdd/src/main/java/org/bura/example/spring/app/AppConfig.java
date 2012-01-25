package org.bura.example.spring.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.bura.example.dao")
public class AppConfig {

	public AppConfig() {
		super();
	}

}
