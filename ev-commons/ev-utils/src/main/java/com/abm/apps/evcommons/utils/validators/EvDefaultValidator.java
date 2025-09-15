package com.abm.apps.evcommons.utils.validators;

import com.abm.apps.evcommons.utils.exceptions.EvValidationException;
import com.abm.apps.evcommons.utils.exceptions.constants.ExceptionTypes;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public interface EvDefaultValidator {

    default void checkNotNullOrEmpty(List<?> listOfAnything) {
        if (CollectionUtils.isEmpty(listOfAnything)) {
            throw new EvValidationException(
                    "Provided list is empty", ExceptionTypes.VALIDATION);
        }
    }

    default void checkMustBeNull(Object object, String fieldName) {

        if(object instanceof String && StringUtils.hasText((String)object)) {
            throw new EvValidationException(String.format(
                    "String [%s] field must be null", fieldName), ExceptionTypes.VALIDATION);
        }

        if (object == null) {
            throw new EvValidationException(String.format(
                    "[%s] field must be null", fieldName), ExceptionTypes.VALIDATION);
        }

    }

    default void checkNotNull(Object object, String fieldName) {

        if(object instanceof String && StringUtils.hasText((String)object)) {
            throw new EvValidationException(String.format(
                    "String [%s] field cannot be null", fieldName), ExceptionTypes.VALIDATION);
        }

        if (object == null) {
            throw new EvValidationException(String.format(
                    "[%s] field cannot be null", fieldName), ExceptionTypes.VALIDATION);
        }

    }

}
