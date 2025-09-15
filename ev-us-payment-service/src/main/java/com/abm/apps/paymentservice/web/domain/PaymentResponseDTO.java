package com.abm.apps.paymentservice.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private String uniquePaymentReference;
    private String successMessage;
    private String errorMessage;
}
