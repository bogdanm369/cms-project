package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountDetailsRequest implements EvGenericRequest {

    private String username;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
