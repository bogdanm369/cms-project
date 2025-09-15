package com.abm.apps.evcommons.beans.domain.http.payloads.payment;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MakePaymentResponse implements EvGenericResponse {
    private OperationResult operationResult;
    private String operationResultText;
}
