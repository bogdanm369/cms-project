


## Creating a custom login in Keycloak

https://auscunningham.medium.com/change-login-theme-in-keycloak-docker-image-55b5fa5ceec4


## Creating users with Keycloak


https://keycloak.discourse.group/t/keycloak-admin-client-in-spring-boot/2547/2

You seem to be mixing things up a bit, I will provide a couple of examples on how you could use the keycloak admin client.
Personally I would choose example 2, creating a dedicated service account client as we are communicating service to service.

Example 1 -> Using a user

Create new client under your desired realm -> keycloak-admin
Select public client with only direct access grant enabled
Create new role, enable composite roles
type realm-managment into client roles under composite roles
add available roles that you need
Select a user and open role mappings tab
type keycloak-admin in client roles and add needed roles
Code:

Keycloak keycloak = KeycloakBuilder.builder()
.serverUrl("http://localhost:8080/auth")
.grantType(OAuth2Constants.PASSWORD)
.realm("realm-name")
.clientId("keycloak-admin")
.username("username")
.password("password")
.resteasyClient(
new ResteasyClientBuilder()
.connectionPoolSize(10).build()
).build();

keycloak.tokenManager().getAccessToken();
RealmResource realmResource = keycloak.realm("realm-name");
Example 2 -> Using a confidential service account

Create new client under your desired realm -> keycloak-admin
Select confidential client with only service account enabled
Select tab service account roles
type realm-management into client roles
add available roles that you need
Code:

Keycloak keycloak = KeycloakBuilder.builder()
.serverUrl("http://localhost:8080/auth")
.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
.realm("realm-name")
.clientId("keycloak-admin")
.clientSecret("1c7e2815-c4dc-401c-af2f-ebddad3b4a79")
.resteasyClient(
new ResteasyClientBuilder()
.connectionPoolSize(10).build()
).build();

keycloak.tokenManager().getAccessToken();
RealmResource realmResource = keycloak.realm("realm-name");
Example 3 -> Using admin account

You could also use the admin user with the password grant and use the existing admin-cli client.

Keycloak keycloak = KeycloakBuilder.builder()
.serverUrl("http://localhost:8080/auth")
.grantType(OAuth2Constants.PASSWORD)
.realm("master")
.clientId("admin-cli")
.username("admin")
.password("password")
.resteasyClient(
new ResteasyClientBuilder()
.connectionPoolSize(10).build()
).build();

keycloak.tokenManager().getAccessToken();
RealmResource realmResource = keycloak.realm("realm-name");