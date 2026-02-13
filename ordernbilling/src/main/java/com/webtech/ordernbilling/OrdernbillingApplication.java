package com.webtech.ordernbilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrdernbillingApplication {

	private static final Logger logger = LoggerFactory.getLogger(OrdernbillingApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(OrdernbillingApplication.class, args);
		logger.info("THE APPLICATION HAS STARTED. THIS IS A LOGGING TEST. DO NOT PANIC!");
	}

}
