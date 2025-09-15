package com.abm.apps.paymentservice.http.client;

import com.abm.apps.paymentservice.config.PaymentsServiceProperties;
import com.abm.apps.paymentservice.web.domain.userprofile.UserProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.abm.apps.paymentservice.config.WebConfig.PAYMENTS_REST_TEMPLATE;

@Component
@Slf4j
public class UserProfileServiceClient {

    @Autowired
    @Qualifier(PAYMENTS_REST_TEMPLATE)
    private RestTemplate restTemplate;

    @Autowired
    private PaymentsServiceProperties paymentsServiceProperties;


    public UserProfileDTO getUserProfile(String username) {
        String callURL = obtainCallUrl(username);
        log.debug("Using call url : [{}]", username);
        ResponseEntity<UserProfileDTO> userProfileResponse = restTemplate.getForEntity(obtainCallUrl(username), UserProfileDTO.class);
        log.debug("Received response; body is null : [{}], response code : [{}]", userProfileResponse.getBody() == null, userProfileResponse.getStatusCodeValue());
        return userProfileResponse.getBody();
    }


    private String obtainCallUrl(String username) {
        return paymentsServiceProperties.getUserServiceUrl().replace("{username}", username);
    }


}
