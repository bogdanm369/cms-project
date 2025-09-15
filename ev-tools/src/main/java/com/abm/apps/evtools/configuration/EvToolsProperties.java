package com.abm.apps.evtools.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static com.abm.apps.evtools.KeycloakOperations.USERS_URL;

@Configuration
@PropertySource("classpath:application.yml")
@Getter
public class EvToolsProperties {

    private String keycloakUrl;

    private String realmName;

    private String adminUsername;

    private String adminPassword;

    public String getUsersUrl() {
        return keycloakUrl + String.format(USERS_URL, realmName);
    }

}
