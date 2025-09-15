package com.abm.apps.evcommons.beans.domain.payment.product;

import com.abm.apps.evcommons.beans.domain.payment.product.units.UnitType;

//TODO not used for the moment.
public interface Unit {
    UnitType getUnitType();
    int getUnitSize();
}
