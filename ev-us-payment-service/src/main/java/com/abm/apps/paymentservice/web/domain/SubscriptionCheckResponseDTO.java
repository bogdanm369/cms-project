package com.abm.apps.paymentservice.web.domain;

import com.abm.apps.paymentservice.domain.EvolvePayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCheckResponseDTO {
    private boolean isActive;
    private EvolvePayment evolvePayment;
}