##--- Docker Instructions for keycloak and postgres

# Keycloak REST API

https://www.keycloak.org/docs-api/5.0/rest-api/index.html#_users_resource


### Outdated; check ev-docker-images

#Create a network in which keycloak and postgres will be put
docker network create keycloak-network

#Start the postgres keycloak database container
docker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres


#After postgres container is started
#This redirects 8080 container port to user machine port 9990
docker run --name keycloak -p 9990:8080 --net keycloak-network -e DB_USER=keycloak -e DB_PASSWORD=password -e DB_ADDR=postgres -e DB_VENDOR=POSTGRES jboss/keycloak


#Still needs restart
docker exec $keycloak-container /opt/jboss/keycloak/bin/add-user-keycloak.sh -u admin -p admin