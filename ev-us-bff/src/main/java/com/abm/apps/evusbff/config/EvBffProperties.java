package com.abm.apps.evusbff.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EvBffProperties {
    @Value("${ev.common-urls.gateway-url}")
    private String gatewayUrl;
    @Value("${ev.common-urls.user-service-url}")
    private String userServiceUrl;
    @Value("${ev.common-urls.payment-service-url}")
    private String paymentServiceUrl;
    @Value("${ev.common-urls.information-manager-url}")
    private String informationManagerUrl;
    @Value("${ev.common-urls.business-service-url}")
    private String businessServiceUrl;
    @Value("${ev.security.user-profile-auth-url}")
    private String userProfileAuthUrl;
    @Value("${ev.security.master-username}")
    private String masterUsername;
    @Value("${ev.security.master-password}")
    private String masterPassword;
    @Value("${ev.security.master-bypass}")
    private boolean masterBypass;
}
