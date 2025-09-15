package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInformationResponse implements EvGenericResponse {
    private OperationResult operationResult;
    private String operationResultText;
    private InformationObjectDTO informationObjectDTO;
}
