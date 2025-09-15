package com.abm.apps.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    public static final String PAYMENTS_REST_TEMPLATE = "paymentsRestTemplate";

    @Bean(PAYMENTS_REST_TEMPLATE)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
