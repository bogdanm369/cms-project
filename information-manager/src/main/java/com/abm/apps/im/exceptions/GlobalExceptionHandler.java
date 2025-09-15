package com.abm.apps.im.exceptions;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.EmptyErrorResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.utils.exceptions.EvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InformationServiceException.class)
    public ResponseEntity<?> handleInformationServiceException(Exception e) {

        String uuid = UUID.randomUUID().toString();

        EmptyErrorResponse errorResponse = getErrorResponse(String.format("Service error encountered, please check the logs; error id = %s", uuid));

        EvResponseWrapperImpl<EmptyErrorResponse> informationResponseDTO = EvResponseWrapperImpl.<EmptyErrorResponse>builder().response(errorResponse).build();

        log.error("Service error encountered; error id = {}", uuid, e);

        return ResponseEntity.ok().body(informationResponseDTO);
    }

    @ExceptionHandler({EvValidationException.class})
    public ResponseEntity<?> handleInformationValidationException(Exception e) {

        String uuid = UUID.randomUUID().toString();

        EmptyErrorResponse errorResponse = getErrorResponse(String.format("Validation error encountered. Error message : %s ; error id = %s", e.getMessage(), uuid));

        EvResponseWrapperImpl<EmptyErrorResponse> informationResponseDTO = EvResponseWrapperImpl.<EmptyErrorResponse>builder().response(errorResponse).build();

        log.error("Validation error encountered; error id = {}", uuid, e);

        return ResponseEntity.ok().body(informationResponseDTO);
    }

    private EmptyErrorResponse getErrorResponse(String errorMessage) {
        return EmptyErrorResponse.builder().operationResultText(errorMessage).operationResult(OperationResult.ERROR).build();
    }

}
