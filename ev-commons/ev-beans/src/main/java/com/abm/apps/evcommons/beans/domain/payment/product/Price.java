package com.abm.apps.evcommons.beans.domain.payment.product;

import com.abm.apps.evcommons.beans.domain.payment.product.currencies.Currency;

import java.math.BigDecimal;

public interface Price {
    BigDecimal getValue();
    Currency getCurrency();
}
