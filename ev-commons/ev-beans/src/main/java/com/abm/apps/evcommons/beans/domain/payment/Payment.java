package com.abm.apps.evcommons.beans.domain.payment;

import com.abm.apps.evcommons.beans.domain.payment.types.PaymentType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public interface Payment extends Serializable {
    String getPaymentServiceReference();
    BigDecimal getPaymentServiceAmount();
    String getPaymentServiceCurrency();
    Date getPaymentDate();
    PaymentType getPaymentType();
}
