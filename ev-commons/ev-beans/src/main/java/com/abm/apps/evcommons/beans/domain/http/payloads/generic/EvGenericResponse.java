package com.abm.apps.evcommons.beans.domain.http.payloads.generic;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;

import java.io.Serializable;


public interface EvGenericResponse<T> extends Serializable {
    OperationResult getOperationResult();
    String getOperationResultText();
}
