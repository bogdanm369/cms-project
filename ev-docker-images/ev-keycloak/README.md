

### Backup and Restore postgres database

##### All commands are executed from bash, are the filename uses Date placeholders
docker exec -t 672 pg_dump --no-owner -U keycloak keycloak > dump_`date +%d-%m-%Y"_"%H_%M_%S`.sql



#### Keycloak useful URLs

#### Register new account

http://localhost:9990/auth/realms/evolve/protocol/openid-connect/registrations?client_id=evolve-client&response_type=code&scope=openid%20email&redirect_uri=http://localhost:9990/realms/evolve/redirect&kc_locale=EN

#### Sign in your account

http://localhost:9990/auth/realms/evolve/protocol/openid-connect/auth?client_id=evolve-client&response_type=code&scope=openid%20email&redirect_uri=http://localhost:9990/realms/evolve/redirect&kc_locale=EN

### Realm login
http://localhost:9990/auth/realms/evolve/protocol/openid-connect/auth

http://localhost:9990/auth/realms/evolve/account