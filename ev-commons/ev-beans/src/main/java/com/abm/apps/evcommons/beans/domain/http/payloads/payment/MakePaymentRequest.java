package com.abm.apps.evcommons.beans.domain.http.payloads.payment;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MakePaymentRequest implements EvGenericRequest {

    private String username;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
