package com.abm.apps.im.exceptions;

import com.abm.apps.evcommons.utils.exceptions.constants.ExceptionTypes;
import lombok.Getter;

@Getter
public class InformationServiceException extends RuntimeException {

    private ExceptionTypes exceptionType;

    public InformationServiceException(String message) {
        this(message, ExceptionTypes.BUSINESS_FLOW);
    }

    public InformationServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InformationServiceException(String message, ExceptionTypes exceptionTypes) {
        super(message);
        this.exceptionType = exceptionTypes;
    }

    public InformationServiceException(String message, Throwable throwable, ExceptionTypes exceptionType) {
        super(message, throwable);
        this.exceptionType = exceptionType;
    }
}
