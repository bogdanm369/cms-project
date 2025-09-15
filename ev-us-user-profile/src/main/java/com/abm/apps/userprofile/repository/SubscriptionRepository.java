package com.abm.apps.userprofile.repository;

import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<UserSubscription, String> {
}
