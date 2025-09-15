package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateUpdateInformationRequest implements EvGenericRequest {

    private List<InformationObjectDTO> informationObjects;
    private boolean returnResults;
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
