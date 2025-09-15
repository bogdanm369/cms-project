package com.abm.apps.evcommons.beans.domain.userProfile.subscriptions;


import com.abm.apps.evcommons.beans.domain.payment.product.SubscriptionProduct;

import java.util.List;

public interface UserSubscription extends SubscriptionProduct {

    String getPaymentServiceReference();

    String getUsername();

    String getSubscriptionName();

    boolean isActive();

    boolean isAutoExtend();

    boolean isTrial();

    List<String> getAdditionalComments();
}
