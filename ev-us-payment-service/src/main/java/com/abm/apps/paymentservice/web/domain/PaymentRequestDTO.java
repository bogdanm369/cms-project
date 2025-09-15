package com.abm.apps.paymentservice.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotNull
    private String productCode;
    @NotNull
    private String username;
    /**
     * To be filled later - credit card details
     */
}
