package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class CreateSubscriptionRequest<S extends UserSubscription> implements EvGenericRequest {

    private String username;
    @NotNull
    private S userSubscription;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
