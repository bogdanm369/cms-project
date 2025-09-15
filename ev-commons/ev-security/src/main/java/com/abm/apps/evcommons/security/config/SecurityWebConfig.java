package com.abm.apps.evcommons.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityWebConfig {

    public static final String SECURITY_REST_TEMPLATE = "evSecurityRestTemplate";

    @Bean(SECURITY_REST_TEMPLATE)
    public RestTemplate evBffRestTemplate() {
        return new RestTemplate();
    }

}
