package com.abm.apps.paymentservice.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentsServiceUtils {


    public String uniquePaymentReference() {

        return UUID.randomUUID().toString();
    }
}
