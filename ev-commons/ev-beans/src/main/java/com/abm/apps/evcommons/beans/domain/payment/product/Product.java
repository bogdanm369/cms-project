package com.abm.apps.evcommons.beans.domain.payment.product;

public interface Product {
    Price getPrice();
    Unit getUnit();
    String getProductName();
    String getProductCode();
    String getProductDescription();
    boolean isActive();
}
