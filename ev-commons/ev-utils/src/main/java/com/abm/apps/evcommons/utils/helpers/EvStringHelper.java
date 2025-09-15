package com.abm.apps.evcommons.utils.helpers;

import com.abm.apps.evcommons.utils.EvExceptionHelper;
import com.abm.apps.evcommons.utils.exceptions.constants.ExceptionTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class EvStringHelper {

    @Autowired
    private EvExceptionHelper evExceptionHelper;

    public String encodeInMD5(String input) {

        try {
            if (StringUtils.hasText(input)) {
                evExceptionHelper.throwValidationException(String.format("Input cannot be null [%s]", input));
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            return new String(md.digest(input.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            evExceptionHelper.throwException(String.format("Exception when converting input [%s] to MD5", input), ExceptionTypes.BUSINESS_FLOW);
            return null;
        }
    }
}
