package com.abm.apps.evusbff.config;

import com.abm.apps.evcommons.security.authentication.EvAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BffWebConfig {

    public static final String BFF_REST_TEMPLATE = "evBffRestTemplate";

    @Bean(BFF_REST_TEMPLATE)
    public RestTemplate evBffRestTemplate() {
        return new RestTemplate();
    }

}
