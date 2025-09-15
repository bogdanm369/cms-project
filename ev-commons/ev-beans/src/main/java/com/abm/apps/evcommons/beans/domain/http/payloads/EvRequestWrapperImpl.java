package com.abm.apps.evcommons.beans.domain.http.payloads;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class EvRequestWrapperImpl<T extends EvGenericRequest> implements EvRequestWrapper<T>{
    @NotNull
    private T request;
}
