package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.InformationFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bogdan.mladinescu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInformationByFilterRequest implements EvGenericRequest {

    private String username;
    private InformationFilter informationFilter;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
