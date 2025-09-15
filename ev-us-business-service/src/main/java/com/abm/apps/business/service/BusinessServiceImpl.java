package com.abm.apps.business.service;

import com.abm.apps.business.config.EvBusinessProperties;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapper;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.GetContentRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.GetContentResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.MakePaymentRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.MakePaymentResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.*;
import com.abm.apps.evcommons.utils.config.EvHttpCalls;
import com.abm.apps.evcommons.utils.helpers.HttpServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.abm.apps.business.config.RestTemplateConfig.BUSINESS_REST_TEMPLATE;

@Component
public class BusinessServiceImpl {

    @Autowired
    private EvBusinessProperties evBusinessProperties;

    @Autowired
    private EvHttpClientService evHttpClientService;

    @Autowired
    @Qualifier(BUSINESS_REST_TEMPLATE)
    private RestTemplate restTemplate;

    @Autowired
    private HttpServiceHelper httpServiceHelper;

    public EvResponseWrapper<GetContentResponse> getContentByList(EvRequestWrapperImpl<GetContentRequest> getContentRequest) {


        //Obtain current logged user
        String username = getContentRequest.getRequest().getUsername();

        EvRequestWrapperImpl<GetAccountDetailsRequest> getAccountDetailsRequest = new EvRequestWrapperImpl<GetAccountDetailsRequest>(new GetAccountDetailsRequest());

        EvResponseWrapper<GetAccountDetailsResponse> accountDetailsResponse = getAccountDetails(getAccountDetailsRequest);

        GetAccountDetailsResponse accountDetails = accountDetailsResponse.getResponse();

        //Check if Subscription is valid for the requested content
        ResponseEntity<EvResponseWrapperImpl<GetContentResponse>> contentNode = restTemplate.exchange(httpServiceHelper.composeUrl(
                evBusinessProperties.getGatewayUrl(), evBusinessProperties.getInformationManagerUrl(), EvHttpCalls.INFORMATION_MANAGER_GET_CONTENT_BY_LIST
        ), HttpMethod.GET, new HttpEntity<String>(null, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetContentResponse>>() {
        });

        return contentNode.getBody();
    }

    public EvResponseWrapper<CreateUpdateUserResponse> createUser(EvRequestWrapperImpl<CreateOrUpdateUserRequest> requestWrapper) {

        return null;
    }

    public EvResponseWrapper<CreateUpdateUserResponse> updateUser(EvRequestWrapperImpl<CreateOrUpdateUserRequest> createUpdateUserRequest) {

        return null;
    }

    public EvResponseWrapper<CreateSubscriptionResponse> createSubscription(EvRequestWrapperImpl<CreateSubscriptionRequest> createSubscriptionRequest) {

        return null;
    }

    public EvResponseWrapper<CreateSubscriptionResponse> changeSubscription(EvRequestWrapperImpl<CreateSubscriptionRequest> createSubscriptionRequest) {

        return null;
    }



    public EvResponseWrapper<GetAccountDetailsResponse> getAccountDetails(EvRequestWrapperImpl<GetAccountDetailsRequest> request) {


        ResponseEntity<EvResponseWrapperImpl<GetAccountDetailsResponse>> responseWrapper =  restTemplate.exchange(httpServiceHelper.composeUrl(evBusinessProperties.getGatewayUrl(), evBusinessProperties.getUserServiceUrl(), EvHttpCalls.USER_SERVICE_GET_ACCOUNT_DETAILS),
                HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetAccountDetailsResponse>>() {
                });

        GetAccountDetailsResponse response = responseWrapper.getBody().getResponse();

        return null;
    }

    public EvResponseWrapper<MakePaymentResponse> makePayment(MakePaymentRequest makePaymentRequest) {

        return null;
    }

    public EvResponseWrapper<MakePaymentResponse> getPayment(MakePaymentRequest makePaymentRequest) {

        return null;
    }



}
