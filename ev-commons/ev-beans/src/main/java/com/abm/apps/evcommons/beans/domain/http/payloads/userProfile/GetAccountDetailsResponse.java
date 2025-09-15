package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericResponse;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetAccountDetailsResponse<P extends EvUserProfile, S extends UserSubscription> implements EvGenericResponse {

    private P evUserProfile;
    private List<S> userSubscriptions;

    private OperationResult operationResult;
    private String operationResultText;
}
