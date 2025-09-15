package com.abm.apps.evusbff.web.controller;


import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapper;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapper;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.*;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.GetPaymentRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.GetPaymentResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.MakePaymentRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.payment.MakePaymentResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.*;
import com.abm.apps.evcommons.utils.config.EvHttpCalls;
import com.abm.apps.evcommons.utils.helpers.HttpServiceHelper;
import com.abm.apps.evusbff.util.EvBffSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.abm.apps.evcommons.beans.domain.http.constants.EvHttpConstants.*;
import static com.abm.apps.evusbff.config.BffWebConfig.BFF_REST_TEMPLATE;

@RestController
@CrossOrigin("*")
public class EvBffAPI {

    @Autowired
    private HttpServiceHelper httpServiceHelper;

    @Autowired
    @Qualifier(BFF_REST_TEMPLATE)
    private RestTemplate restTemplate;

    @Autowired
    private EvBffSecurityUtil evBffSecurityUtil;


    /**
     * Returns the Content based on this user's Subscription and/or Profile settings
     *
     * @return
     */
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_GET_CONTENT_BY_REF)
    public ResponseEntity<?> getContent(@RequestBody EvRequestWrapperImpl<GetContentRequest> request) {

        evBffSecurityUtil.applyUsername(request);

        ResponseEntity<EvResponseWrapperImpl<GetContentResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.INFORMATION_MANAGER_GET_CONTENT_BY_REF
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetContentResponse>>() {
        });

        return response;
    }

    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION)
    public ResponseEntity<?> createInformation(@RequestBody EvRequestWrapperImpl<CreateUpdateInformationRequest> request) {

        evBffSecurityUtil.applyUsername(request);

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {
        });

        return response;
    }

    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_UPDATE_INFORMATION)
    public ResponseEntity<?> updateInformation(@RequestBody EvRequestWrapperImpl<CreateUpdateInformationRequest> request) {

        evBffSecurityUtil.applyUsername(request);

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.INFORMATION_MANAGER_UPDATE_INFORMATION
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {
        });

        return response;
    }

    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_ID)
    public ResponseEntity<?> getInformationById(@RequestParam(INFORMATION_ID) String informationId) {

        String username = evBffSecurityUtil.getLoggedUsername();

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put(INFORMATION_ID, Arrays.asList(informationId));
        queryParameters.put(USERNAME, Arrays.asList(username));

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), queryParameters, EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_ID
        ), HttpMethod.GET, new HttpEntity<>(null, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {
        });

        return response;
    }

    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_REF)
    public ResponseEntity<?> getInformationByFilter(@RequestParam(INFORMATION_REF_NAME) String informationRef) {

        String username = evBffSecurityUtil.getLoggedUsername();

        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.put(INFORMATION_REF_NAME, Arrays.asList(informationRef));
        queryParameters.put(USERNAME, Arrays.asList(username));

        ResponseEntity<EvResponseWrapperImpl<GetInformationResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), queryParameters, EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_REF
        ), HttpMethod.GET, new HttpEntity<>(null, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetInformationResponse>>() {
        });

        return response;
    }


    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_FILTER)
    public ResponseEntity<?> getInformationByFilter(@RequestBody EvRequestWrapperImpl<GetInformationByFilterRequest> request) {

        evBffSecurityUtil.applyUsername(request);

        ResponseEntity<EvResponseWrapperImpl<GetInformationByFilterResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_FILTER
        ), HttpMethod.POST, new HttpEntity<>(request, httpServiceHelper.getHttpHeaders(null)), new ParameterizedTypeReference<EvResponseWrapperImpl<GetInformationByFilterResponse>>() {
        });

        return response;
    }

    /**
     * Creates a User
     * @return
     */
    @PostMapping(EvHttpCalls.USER_SERVICE_CREATE_USER)
    public ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> createUser(@RequestBody EvRequestWrapper<CreateOrUpdateUserRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.USER_SERVICE_CREATE_USER
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateUserResponse>>() {
        });

        return response;
    }


    /**
     * Updates a User
     * @return
     */
    @PostMapping(EvHttpCalls.USER_SERVICE_UPDATE_USER)
    public ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> updateUser(@RequestBody EvRequestWrapperImpl<CreateOrUpdateUserRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateUserResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.USER_SERVICE_UPDATE_USER
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
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.USER_SERVICE_GET_ACCOUNT_DETAILS
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
    public ResponseEntity<?> createSubscription(@RequestBody EvRequestWrapper<CreateSubscriptionRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapper<CreateSubscriptionResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.USER_SERVICE_CREATE_SUBSCRIPTION
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapper<CreateSubscriptionResponse>>() {
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
    public ResponseEntity<?> changeSubscription(@RequestBody EvRequestWrapper<ChangeSubscriptionRequest> requestWrapper) {

        ResponseEntity<EvResponseWrapper<ChangeSubscriptionResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.USER_SERVICE_CHANGE_SUBSCRIPTION
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapper<ChangeSubscriptionResponse>>() {
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

        evBffSecurityUtil.applyUsername(requestWrapper);

        ResponseEntity<EvResponseWrapper<MakePaymentResponse>> response = restTemplate.exchange(httpServiceHelper.composeUrl(
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.PAYMENT_SERVICE_MAKE_PAYMENT
        ), HttpMethod.POST, new HttpEntity<>(requestWrapper), new ParameterizedTypeReference<EvResponseWrapper<MakePaymentResponse>>() {
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
                httpServiceHelper.getGatewayBusinessServiceUrl(), EvHttpCalls.PAYMENT_SERVICE_GET_PAYMENT
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

    @ResponseBody
    @GetMapping("/echoSecured")
    public String echoSecured() {
        return "Secured-Echo";
    }

}
