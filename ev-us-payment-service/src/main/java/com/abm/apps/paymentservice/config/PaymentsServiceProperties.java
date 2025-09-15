package com.abm.apps.paymentservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentsServiceProperties {

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
}
