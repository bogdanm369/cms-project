package com.abm.apps.userprofile.domain.subscription;

import com.abm.apps.evcommons.beans.domain.payment.product.periods.SubscriptionPeriodType;
import com.abm.apps.evcommons.beans.domain.userProfile.subscriptions.UserSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Collection of all Active User Subscriptions
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user-subscriptions")
public class EvActiveSubscriptions implements UserSubscription {

    @Id
    protected String id;
    @Indexed
    protected String username;
    protected String subscriptionName;
    @CreatedDate
    protected Date creationDate;
    protected Date startDate;
    protected Date endDate;
    @Indexed
    protected String paymentServiceReference;
    protected boolean isActive;
    protected SubscriptionPeriodType subscriptionPeriodType;
    protected boolean isAutoExtend;
    protected boolean isTrial;
    protected List<String> additionalComments = new ArrayList<>();
}
