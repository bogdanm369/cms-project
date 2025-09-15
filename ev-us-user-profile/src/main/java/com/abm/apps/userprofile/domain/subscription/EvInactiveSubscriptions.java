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
 * All inactive subscriptions will be moved to a separate Collection for historical check purposes
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("archived-user-subscriptions")
public class EvInactiveSubscriptions implements UserSubscription {

    @Id
    protected String id;
    @Indexed
    protected String username;
    protected String subscriptionName;
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
    @CreatedDate
    private Date archivedDate;
}
