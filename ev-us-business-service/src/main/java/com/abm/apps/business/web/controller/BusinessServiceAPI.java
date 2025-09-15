package com.abm.apps.business.web.controller;

import com.abm.apps.business.config.EvBusinessProperties;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.*;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.GetPaymentRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.GetPaymentResponse;
import com.abm.apps.evcommons.utils.config.EvHttpCalls;
import com.abm.apps.business.service.BusinessServiceImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapper;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.MakePaymentRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.MakePaymentResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.*;
import com.abm.apps.evcommons.utils.helpers.HttpServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.abm.apps.business.config.RestTemplateConfig.BUSINESS_REST_TEMPLATE;
import static com.abm.apps.evcommons.beans.domain.http.constants.EvHttpConstants.*;

@Slf4j
@RestController
public class BusinessServiceAPI {

    @Autowired
    @Qualifier(BUSINESS_REST_TEMPLATE)
    private RestTemplate restTemplate;

    @Autowired
    private HttpServiceHelper httpServiceHelper;

    @Autowired
    private EvBusinessProperties evBusinessProperties;

    @Autowired
    private BusinessServiceImpl businessServiceImpl;


    /**
     * Returns the Content based on this user's Subscription and/or Profile settings
     *
     * @return
     */
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_GET_CONTENT_BY_REF)
    public ResponseEntity<?> getContent(@RequestBody EvRequestWrapperImpl<GetContentRequest> request) {

        ResponseEntity<EvResponseWrapperImpl<GetContentResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayInformationManagerUrl(), EvHttpCalls.INFORMATION_MANAGER_GET_CONTENT_BY_REF
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetContentResponse>>() {
        });

        return response;
    }

    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION)
    public ResponseEntity<?> createInformation(@RequestBody EvRequestWrapperImpl<CreateUpdateInformationRequest> request) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayInformationManagerUrl(), EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {
        });

        return response;
    }

    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_UPDATE_INFORMATION)
    public ResponseEntity<?> updateInformation(@RequestBody EvRequestWrapperImpl<CreateUpdateInformationRequest> request) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayInformationManagerUrl(), EvHttpCalls.INFORMATION_MANAGER_UPDATE_INFORMATION
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {
        });

        return response;
    }

    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_ID)
    public ResponseEntity<?> getInformationById(@RequestParam(INFORMATION_ID)String informationId,
                                                    @RequestParam(USERNAME)String username) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayInformationManagerUrl(),EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_ID
        ), HttpMethod.GET, new HttpEntity<>(null, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {
        });

        return response;
    }

    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_REF)
    public ResponseEntity<?> getInformationByRef(@RequestParam(INFORMATION_REF_NAME)String informationRef,
                                                 @RequestParam(USERNAME)String username) {


        ResponseEntity<EvResponseWrapperImpl<GetInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayInformationManagerUrl(), EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_REF
        ), HttpMethod.GET, new HttpEntity<>(null, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetInformationResponse>>() {
        });

        return response;
    }

    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_FILTER)
    public ResponseEntity<?> getInformationByRef(@RequestBody EvRequestWrapperImpl<GetInformationByFilterRequest> requestWrapper) {


        ResponseEntity<EvResponseWrapperImpl<GetInformationByFilterResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayInformationManagerUrl(), EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_FILTER
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetInformationByFilterResponse>>() {
        });

        return response;
    }

    /**
     * Creates a User
     *
     * @return
     */
    @PostMapping(EvHttpCalls.USER_SERVICE_CREATE_USER)
    public ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> createUser(@RequestBody EvRequestWrapperImpl<CreateOrUpdateUserRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayUserServiceUrl(), EvHttpCalls.USER_SERVICE_CREATE_USER
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateUserResponse>>() {
        });

        return response;
    }


    /**
     * Updates a User
     *
     * @return
     */
    @PostMapping(EvHttpCalls.USER_SERVICE_UPDATE_USER)
    public ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> updateUser(@RequestBody EvRequestWrapperImpl<CreateOrUpdateUserRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayUserServiceUrl(), EvHttpCalls.USER_SERVICE_UPDATE_USER
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateUserResponse>>() {
        });

        return response;
    }


    /**
     * Gets the User's Account Details
     *
     * @return
     */
    @GetMapping(EvHttpCalls.USER_SERVICE_GET_ACCOUNT_DETAILS)
    public ResponseEntity<?> getAccountDetails(@RequestBody EvRequestWrapperImpl<GetAccountDetailsRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<GetAccountDetailsResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayUserServiceUrl(), EvHttpCalls.USER_SERVICE_GET_ACCOUNT_DETAILS
        ), HttpMethod.GET, new HttpEntity<>(requestWrapper, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetAccountDetailsResponse>>() {
        });

        return response;
    }

    /**
     * Creates a Subscription for this User
     *
     * @return
     */
    @PostMapping(EvHttpCalls.USER_SERVICE_CREATE_SUBSCRIPTION)
    public ResponseEntity<?> createSubscription(@RequestBody EvRequestWrapperImpl<ChangeSubscriptionRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<CreateSubscriptionResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayUserServiceUrl(), EvHttpCalls.USER_SERVICE_CREATE_SUBSCRIPTION
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateSubscriptionResponse>>() {
        });

        return response;
    }


    /**
     * Changes the Subscription for this User
     * Extends or Cancel
     *
     * @return
     */
    @PostMapping(EvHttpCalls.USER_SERVICE_CHANGE_SUBSCRIPTION)
    public ResponseEntity<?> changeSubscription(@RequestBody EvRequestWrapperImpl<ChangeSubscriptionRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<ChangeSubscriptionResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayUserServiceUrl(), EvHttpCalls.USER_SERVICE_CHANGE_SUBSCRIPTION
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapperImpl<ChangeSubscriptionResponse>>() {
        });

        return response;
    }


    /**
     * Makes a payment
     *
     * @return
     */
    @PostMapping(EvHttpCalls.PAYMENT_SERVICE_MAKE_PAYMENT)
    public ResponseEntity<?> makePayment(@RequestBody EvRequestWrapperImpl<MakePaymentRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<MakePaymentResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayPaymentsServiceUrl(), EvHttpCalls.PAYMENT_SERVICE_MAKE_PAYMENT
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapperImpl<MakePaymentResponse>>() {
        });

        return response;
    }

    /**
     * Gets a payment
     *
     * @return
     */
    @PostMapping(EvHttpCalls.PAYMENT_SERVICE_GET_PAYMENT)
    public ResponseEntity<?> getPayment(@RequestBody EvRequestWrapperImpl<GetPaymentRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapper<GetPaymentResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayPaymentsServiceUrl(), EvHttpCalls.PAYMENT_SERVICE_GET_PAYMENT
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapper<GetPaymentResponse>>() {
        });

        return response;
    }

    //Works
    @ResponseBody
    @GetMapping("/echo")
    public String echoText() {
        return "Hello !";
    }

}
