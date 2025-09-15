package com.abm.apps.evcommons.beans.domain.payment.product;

import com.abm.apps.evcommons.beans.domain.payment.product.periods.SubscriptionPeriodType;

import java.util.Date;

public interface SubscriptionProduct {
    SubscriptionPeriodType getSubscriptionPeriodType();
    Date getStartDate();
    Date getEndDate();
    Date getCreationDate();
}
