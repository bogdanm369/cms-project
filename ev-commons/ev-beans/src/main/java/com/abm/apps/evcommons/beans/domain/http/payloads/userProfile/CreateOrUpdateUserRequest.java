package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateOrUpdateUserRequest<P extends EvUserProfile> implements EvGenericRequest {

    private String username;
    private P evUserProfile;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
