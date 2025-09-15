package com.abm.apps.im.utils;

import com.abm.apps.evcommons.utils.exceptions.EvValidationException;
import com.abm.apps.evcommons.utils.exceptions.constants.ExceptionTypes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class InformationExceptionHelper {

    private String nullField = "Field [%s] cannot be null";

    public void throwValidationExceptionForNullField(String fieldName) throws EvValidationException {
        if (!StringUtils.hasText(fieldName)) {
            throw new NullPointerException(String.format(nullField, fieldName));
        }
        throwException(String.format(nullField, fieldName), ExceptionTypes.VALIDATION);
    }

    public void throwException(String message, ExceptionTypes exceptionTypes) throws EvValidationException {
        throw new EvValidationException(message, exceptionTypes);
    }

    public void throwValidationException(String message) throws EvValidationException {
        throw new EvValidationException(message, ExceptionTypes.VALIDATION);
    }
}
