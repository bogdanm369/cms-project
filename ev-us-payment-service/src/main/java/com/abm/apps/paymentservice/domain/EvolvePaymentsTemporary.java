package com.abm.apps.paymentservice.domain;

import com.abm.apps.evcommons.beans.domain.payment.Payment;
import com.abm.apps.evcommons.beans.domain.payment.types.PaymentType;
import com.abm.apps.paymentservice.domain.products.impl.evolve.EvolveSubscriptionProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Just like the EvolvePayment class, this class is for payments which are pending, or have been archived
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("evolve-payments-temporary")
public class EvolvePaymentsTemporary implements Payment {

    @Id
    private String id;
    @Indexed(unique=true)
    @NotNull
    private String uniquePaymentReference;
    @NotNull
    private EvolveSubscriptionProduct evolveSubscription;
    @Indexed(unique = true)
    @NotNull
    private String paymentServiceReference;
    @NotNull
    private BigDecimal paymentServiceAmount;
    @NotNull
    private String paymentServiceCurrency;
    @NotNull
    @CreatedDate
    private Date paymentDate;
    @NotNull
    private PaymentType paymentType;
    @NotNull
    private String username;
    @NotNull
    private PaymentStatus paymentStatus;

}
