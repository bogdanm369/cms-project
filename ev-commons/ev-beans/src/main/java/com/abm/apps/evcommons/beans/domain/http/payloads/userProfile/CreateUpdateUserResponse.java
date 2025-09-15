package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreateUpdateUserResponse<P extends EvUserProfile> implements EvGenericResponse {
    private P evUserProfile;
    private OperationResult operationResult;
    private String operationResultText;
}
