package com.abm.apps.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    public static final String BUSINESS_REST_TEMPLATE = "evBusinessRestTemplate";

    @Bean(BUSINESS_REST_TEMPLATE)
    public RestTemplate evBffRestTemplate() {

        return new RestTemplate();
    }
}
