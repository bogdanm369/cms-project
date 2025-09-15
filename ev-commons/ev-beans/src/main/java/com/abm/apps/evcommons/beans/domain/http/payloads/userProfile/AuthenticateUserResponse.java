package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticateUserResponse implements EvGenericResponse {

    private OperationResult operationResult;
    private String operationResultText;

    @Override
    public OperationResult getOperationResult() {
        return operationResult;
    }

    @Override
    public String getOperationResultText() {
        return operationResultText;
    }
}
