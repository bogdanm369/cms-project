package com.abm.apps.evtools.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Arrays;

public class KeycloakClient {

    private static String keycloakUrl = "http://localhost:9990/auth";
    private static String realmName = "evolve";
    private static String grantType = "";
    private static String adminUsername = "evolve-admin";
    private static String adminPassword = "admin";

    private static String clientId = "evolve-client";
    private static String clientSecret = "480ba032-c625-4651-93fa-751869122f28";

    public static void main(String[] args) {
        Keycloak keycloak = KeycloakBuilder
                .builder()
                .serverUrl(keycloakUrl)
                .realm(realmName)
                .clientId(clientId)
                .clientSecret(clientSecret)
                //.username(adminUsername)
                //.password(adminPassword)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS) //)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();


        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(adminPassword);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername("lupin");
        userRepresentation.setFirstName("Remus");
        userRepresentation.setLastName("Lupin");
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));
        keycloak.realm(realmName).users().create(userRepresentation);

        UsersResource usersResource = keycloak.realm(realmName).users();
        UserRepresentation user = usersResource.search("lupin").get(0);
        user.setEmail("lupin@hogwarts.co.uk");
        usersResource.get(user.getId()).update(user);


    }
}
