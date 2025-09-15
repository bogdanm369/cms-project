package com.abm.apps.evusbff.config;

import com.abm.apps.evcommons.security.config.EvSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EvBffSecurityProperties implements EvSecurityProperties {

    @Autowired
    private EvBffProperties evBffProperties;

    @Override
    public String getUserProfileAuthUrl() {
        return evBffProperties.getUserProfileAuthUrl();
    }

    @Override
    public boolean isMasterBypass() {
        return evBffProperties.isMasterBypass();
    }

    @Override
    public String getMasterUsername() {
        return evBffProperties.getMasterUsername();
    }

    @Override
    public String getMasterPassword() {
        return evBffProperties.getMasterPassword();
    }


}
