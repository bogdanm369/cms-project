package com.abm.apps.evcommons.beans.domain.http.payloads;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;

import java.io.Serializable;

public interface EvResponseWrapper<T extends EvGenericResponse> extends Serializable {
    T getResponse();
    default <T extends EvGenericResponse> T getCastResponse(Class<T> castTo) {
        return (castTo.cast(getResponse()));
    }
}
