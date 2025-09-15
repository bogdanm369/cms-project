package com.abm.apps.evtools.client;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class OAuth2Client {

    private String keycloakUrl = "http://localhost:9990/auth/realms/%s/protocol/openid-connect/token";
    private String realmName = "evolve";
    private String grantType = "";
    private String adminUsername = "";
    private String adminPassword = "";

    private String clientId = "evolve-client";
    private String clientSecret = "18a181dc-9e58-4c78-88a0-8a431bfc0b32";


    private RestTemplate restTemplate = new RestTemplate();



    public static void main(String[] args) {

        new OAuth2Client().callKeycloak();

    }


    public String callKeycloak() {

        String callUrl = String.format(keycloakUrl, realmName);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, null);

        String response = restTemplate.postForObject(callUrl, request, String.class);

        return response;
    }

}
