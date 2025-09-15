package com.abm.apps.evusbff.util;

import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EvBffSecurityUtil {

    public void applyUsername(EvRequestWrapperImpl<? extends EvGenericRequest> requestWrapper) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Applying username [{}] to Request-Class [{}]", username, requestWrapper.getRequest().getClass().toGenericString());
        requestWrapper.getRequest().setUsername(username);
    }

    public String getLoggedUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
