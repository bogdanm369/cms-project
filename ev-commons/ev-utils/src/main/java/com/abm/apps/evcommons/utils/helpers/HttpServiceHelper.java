package com.abm.apps.evcommons.utils.helpers;

import com.abm.apps.evcommons.utils.EvExceptionHelper;
import com.abm.apps.evcommons.utils.config.EvCommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Slf4j
@Component
public class HttpServiceHelper {


    @Autowired
    private EvExceptionHelper evExceptionHelper;

    @Autowired
    private EvCommonProperties evCommonProperties;


    public String composeUrl(String baseUrl, String... subUrl) {

        return composeUrl(baseUrl, null, subUrl);
    }

    public String composeUrl(String baseUrl, MultiValueMap<String, String> queryParams, String... subUrls) {

        log.debug("Composing URL for the following parameters baseURL [{}], queryParams [{}], subUrls [{}]", baseUrl, queryParams, subUrls != null ? Arrays.asList(subUrls) : "NULL");

        if (!StringUtils.hasText(baseUrl)) {
            evExceptionHelper.throwValidationException(String.format("baseUrl [%s] cannot be null/empty", baseUrl));
        }

        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(baseUrl);

        if (null != subUrls) {

            for (String s : subUrls) {
                uri.path(s);
            }
        }

        if(null != queryParams) {
            uri.queryParams(queryParams);
        }

        String result = uri.toUriString();

        log.debug("Returning composed URL [{}]", result);

        return result;
    }

    public MultiValueMap<String,String> getQueryParams(String paramName, String... values) {

        if(!StringUtils.hasText(paramName) || values == null) {
            log.info("Empty paramName [{}] supplied, or values is null ? [{}]", paramName, values == null);
            return new LinkedMultiValueMap<>();
        }

        MultiValueMap<String,String> queryParameters = new LinkedMultiValueMap<>();
        List<String> valueList = Arrays.asList(values);
        queryParameters.addAll(paramName, valueList);

        return queryParameters;
    }

    public HttpHeaders getHttpHeaders(Map<String, String> headerNameValues) {

        HttpHeaders httpHeaders = new HttpHeaders();

        if (!CollectionUtils.isEmpty(headerNameValues)) {
            headerNameValues.keySet().forEach(
                    it -> httpHeaders.add(it, headerNameValues.get(it))
            );
        }

        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());

        return httpHeaders;
    }

    public String getGatewayUrl() {
        return evCommonProperties.getGatewayUrl();
    }

    public String getGatewayUserServiceUrl() {
        return          UriComponentsBuilder.fromHttpUrl(evCommonProperties.getGatewayUrl()).path(evCommonProperties.getUserServiceUrl()).toUriString();
    }

    public String getGatewayBusinessServiceUrl() {
        return          UriComponentsBuilder.fromHttpUrl(evCommonProperties.getGatewayUrl()).path(evCommonProperties.getBusinessServiceUrl()).toUriString();
    }

    public String getGatewayPaymentsServiceUrl() {
        return          UriComponentsBuilder.fromHttpUrl(evCommonProperties.getGatewayUrl()).path(evCommonProperties.getPaymentsServiceUrl()).toUriString();
    }

    public String getGatewayInformationManagerUrl() {
        return          UriComponentsBuilder.fromHttpUrl(evCommonProperties.getGatewayUrl()).path(evCommonProperties.getInformationManagerUrl()).toUriString();
    }

}
