package com.abm.apps.paymentservice.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCheckRequestDTO {
    private String username;
    private Date checkAtDate;
}