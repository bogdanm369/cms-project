package com.abm.apps.evcommons.utils.exceptions;

import com.abm.apps.evcommons.utils.exceptions.constants.ExceptionTypes;
import lombok.Getter;

@Getter
public class EvValidationException extends RuntimeException {

    private ExceptionTypes exceptionType;

    public EvValidationException(String message) {
        this(message, ExceptionTypes.VALIDATION);
    }

    public EvValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EvValidationException(String message, ExceptionTypes exceptionTypes) {
        super(message);
        this.exceptionType = exceptionTypes;
    }

    public EvValidationException(String message, Throwable throwable, ExceptionTypes exceptionType) {
        super(message, throwable);
        this.exceptionType = exceptionType;
    }
}
