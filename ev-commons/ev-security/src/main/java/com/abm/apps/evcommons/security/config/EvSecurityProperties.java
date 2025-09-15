package com.abm.apps.evcommons.security.config;

/**
 * This interface needs to be implemented and be defined as a Spring Bean ( @Component, @Service etc. )
 */
public interface EvSecurityProperties {
    String getUserProfileAuthUrl();
    boolean isMasterBypass();
    String getMasterUsername();
    String getMasterPassword();
}
