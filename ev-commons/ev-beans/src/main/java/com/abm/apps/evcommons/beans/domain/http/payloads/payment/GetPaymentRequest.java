package com.abm.apps.evcommons.beans.domain.http.payloads.payment;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentRequest implements EvGenericRequest {
    private String username;
}
