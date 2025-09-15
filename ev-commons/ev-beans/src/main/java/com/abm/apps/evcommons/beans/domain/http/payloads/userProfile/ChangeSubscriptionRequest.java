package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChangeSubscriptionRequest<S extends UserSubscription> implements EvGenericRequest {

    private String username;
    private S userSubscription;

    @Override
    public void setUsername(String username) {
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
