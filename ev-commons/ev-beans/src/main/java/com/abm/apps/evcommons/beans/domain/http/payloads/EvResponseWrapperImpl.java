package com.abm.apps.evcommons.beans.domain.http.payloads;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EvResponseWrapperImpl<T extends EvGenericResponse> implements EvResponseWrapper<T> {
    private T response;
}
