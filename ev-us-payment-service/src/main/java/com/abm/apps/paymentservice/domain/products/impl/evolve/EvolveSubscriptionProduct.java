package com.abm.apps.paymentservice.domain.products.impl.evolve;

import com.abm.apps.evcommons.beans.domain.payment.product.Product;
import com.abm.apps.evcommons.beans.domain.payment.product.SubscriptionProduct;
import com.abm.apps.evcommons.beans.domain.payment.product.periods.SubscriptionPeriodType;
import com.abm.apps.paymentservice.domain.products.impl.generic.GenericPrice;
import com.abm.apps.paymentservice.domain.products.impl.generic.GenericUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("ev-subscriptions-products-catalog")
public class EvolveSubscriptionProduct implements Product, SubscriptionProduct {
    @Id
    private String id;
    @Indexed
    @NotNull
    private String productName;
    @Indexed(unique = true)
    @NotNull
    private String productCode;
    @NotNull
    private SubscriptionPeriodType subscriptionPeriodType;
    @NotNull
    private boolean isActive;
    @NotNull
    private String productDescription;
    private Date startDate;
    private Date endDate;
    @NotNull
    @CreatedDate
    private Date creationDate;
    @NotNull
    private GenericPrice price;
    @NotNull
    private GenericUnit unit;
}
