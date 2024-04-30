package com.divergentsl.psp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	/**
	 * it is used to create bean for Rest Template
	 * 
	 * @return RestTemplate
	 */
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
