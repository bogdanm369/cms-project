package com.abm.apps.evcommons.security.authentication;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.AuthenticateUserRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.AuthenticateUserResponse;
import com.abm.apps.evcommons.security.config.EvSecurityProperties;
import com.abm.apps.evcommons.utils.helpers.HttpServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.abm.apps.evcommons.security.config.SecurityWebConfig.SECURITY_REST_TEMPLATE;
import static com.abm.apps.evcommons.security.roles.EvRoles.ADMIN_ROLE;
import static com.abm.apps.evcommons.security.roles.EvRoles.USER_ROLE;

@Slf4j
@Component
public class EvAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier(SECURITY_REST_TEMPLATE)
    private RestTemplate restTemplate;

    @Autowired
    private HttpServiceHelper httpServiceHelper;

    @Autowired
    private EvSecurityProperties evSecurityProperties;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE));

        log.info("Attempting to authenticate user : [{}], using auth endpoint : [{}]", username, evSecurityProperties.getUserProfileAuthUrl());

        checkUsernamePassword(username, password);

        //If it's a master user, insta-authorize
        if (isMasterUser(username, password)) {
            log.info("Master authentication is enabled, User [{}] is a master user; Skipping auth-endpoint authentication", username);
            authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }

        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest(username, password);

        //Check if the user is authenticated.
        ResponseEntity<EvResponseWrapperImpl<AuthenticateUserResponse>> contentNode = restTemplate.exchange(
                evSecurityProperties.getUserProfileAuthUrl(), HttpMethod.POST,
                new HttpEntity<>(new EvRequestWrapperImpl<>(authenticateUserRequest), httpServiceHelper.getHttpHeaders(null)),
                new ParameterizedTypeReference<EvResponseWrapperImpl<AuthenticateUserResponse>>() {
                });

        AuthenticateUserResponse aur = null;

        if (contentNode == null || contentNode.getBody() == null || (aur = contentNode.getBody().getResponse()) == null) {
            log.error("User authentication response body is null");
            throw new AuthenticationServiceException("Authentication response is null; Check the logs");
        }

        if (aur.getOperationResult() == null) {
            log.error("User Authentication failed; AuthenticationResult is null");
            throw new AuthenticationServiceException("AuthenticationResult is null; Check the logs");
        }

        if (OperationResult.FAILED.equals(aur.getOperationResult())
                || OperationResult.ERROR.equals(aur.getOperationResult())) {
            log.error("Authentication failed; Authentication result = [{}]", aur.getOperationResult());
            throw new BadCredentialsException("Authentication failed");
        }

        log.info("Authentication result for user [{}] is [{}]", username, aur.getOperationResult());

        if (aur.getOperationResult().equals(OperationResult.SUCCESS)) {
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }

        return null;
    }

    private boolean isMasterUser(String username, String password) {

        if (evSecurityProperties.isMasterBypass()
                && evSecurityProperties.getMasterUsername().equals(username)
                && evSecurityProperties.getMasterPassword().equals(password)) {
            return true;
        }
        return false;
    }

    private void checkUsernamePassword(String username, String password) throws AuthenticationException {
        if (StringUtils.isEmpty(username)) {
            throwBadCredentialsExceptions("Username value cannot be null/empty.");
        }
        if (StringUtils.isEmpty(password)) {
            throwBadCredentialsExceptions("Password value cannot be null/empty.");
        }
    }

    private void throwBadCredentialsExceptions(String message) {
        throw new BadCredentialsException(message);
    }

    @Override
    public boolean supports(Class<? extends Object> authenticationClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass));
    }
}
