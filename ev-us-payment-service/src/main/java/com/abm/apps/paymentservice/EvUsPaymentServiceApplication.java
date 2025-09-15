package com.abm.apps.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.abm.apps")
public class EvUsPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvUsPaymentServiceApplication.class, args);
	}

}
