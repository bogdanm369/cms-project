package com.abm.apps.evcommons.security.config;

import org.springframework.stereotype.Component;

@Component
public class EvMockSecurityProperties implements EvSecurityProperties {

    @Override
    public String getUserProfileAuthUrl() {
        return "mockValue";
    }

    @Override
    public boolean isMasterBypass() {
        return false;
    }

    @Override
    public String getMasterUsername() {
        return "mockValue";
    }

    @Override
    public String getMasterPassword() {
        return "mockValue";
    }


}
