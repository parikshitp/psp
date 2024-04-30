package com.divergentsl.acquirer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class AcquirerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcquirerApplication.class, args);
	}

}
