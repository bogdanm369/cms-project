package com.abm.apps.evcommons.beans.domain.userProfile;

import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;

import java.io.Serializable;
import java.util.Date;

public interface EvUserProfile extends Serializable {
    String getId();
    String getUsername();
    String getEmail();
    String getFirstname();
    String getLastname();
    Date getRegistrationDate();
    Date getLastModifiedDate();
}
