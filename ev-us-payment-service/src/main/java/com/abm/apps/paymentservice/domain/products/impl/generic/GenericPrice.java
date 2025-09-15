package com.abm.apps.paymentservice.domain.products.impl.generic;

import com.abm.apps.evcommons.beans.domain.payment.product.Price;
import com.abm.apps.evcommons.beans.domain.payment.product.currencies.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericPrice implements Price {
    private BigDecimal value;
    private Currency currency;
}
