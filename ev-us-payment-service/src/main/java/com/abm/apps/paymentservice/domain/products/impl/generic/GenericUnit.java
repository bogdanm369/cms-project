package com.abm.apps.paymentservice.domain.products.impl.generic;

import com.abm.apps.evcommons.beans.domain.payment.product.Unit;
import com.abm.apps.evcommons.beans.domain.payment.product.units.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericUnit implements Unit {
    private UnitType unitType;
    private int unitSize = 1;
}
