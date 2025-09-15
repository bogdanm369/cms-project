package com.abm.apps.evcommons.utils.exceptions;

import com.abm.apps.evcommons.utils.exceptions.constants.ExceptionTypes;
import lombok.Getter;

@Getter
public class EvServiceException extends RuntimeException {

    private ExceptionTypes exceptionType;

    public EvServiceException(String message) {
        this(message, ExceptionTypes.BUSINESS_FLOW);
    }

    public EvServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EvServiceException(String message, ExceptionTypes exceptionTypes) {
        super(message);
        this.exceptionType = exceptionTypes;
    }

    public EvServiceException(String message, Throwable throwable, ExceptionTypes exceptionType) {
        super(message, throwable);
        this.exceptionType = exceptionType;
    }
}
