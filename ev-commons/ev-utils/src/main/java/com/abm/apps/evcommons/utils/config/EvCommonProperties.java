package com.abm.apps.evcommons.utils.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EvCommonProperties {

    @Value("${ev.common-urls.gateway-url:mockValue}")
    private String gatewayUrl;

    @Value("${ev.common-urls.user-service-url:mockValue}")
    private String userServiceUrl;

    @Value("${ev.common-urls.payments-service-url:mockValue}")
    private String paymentsServiceUrl;

    @Value("${ev.common-urls.information-manager-url:mockValue}")
    private String informationManagerUrl;

    @Value("${ev.common-urls.business-service-url:mockValue}")
    private String businessServiceUrl;

}
