package com.abm.apps.evcommons.beans.domain.http.payloads;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;

import java.io.Serializable;

public interface EvRequestWrapper<T extends EvGenericRequest> extends Serializable {
    T getRequest();
}
