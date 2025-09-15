package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bogdan.mladinescu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInformationByFilterResponse implements EvGenericResponse {
    private OperationResult operationResult;
    private String operationResultText;
    private List<InformationObjectDTO> informationObjects;
}
