FROM jboss/keycloak:15.0.2

RUN sed -i -E "s/(<staticMaxAge>)2592000(<\/staticMaxAge>)/\1\-1\2/" /opt/jboss/keycloak/standalone/configuration/standalone.xml
RUN sed -i -E "s/(<cacheThemes>)true(<\/cacheThemes>)/\1false\2/" /opt/jboss/keycloak/standalone/configuration/standalone.xml
RUN sed -i -E "s/(<cacheTemplates>)true(<\/cacheTemplates>)/\1false\2/" /opt/jboss/keycloak/standalone/configuration/standalone.xml

#COPY theme_keycloak:/opt/jboss/keycloak/themes/base/login/login.ftl ./keycloak/themes/evolve/login/login.ftl

ADD keycloak/themes /opt/jboss/keycloak/themes

#RUN echo whoami
#USER root
#RUN chown -R root:jboss /opt/jboss/keycloak/themes/evolve
