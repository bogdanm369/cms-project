package com.abm.apps.evcommons.beans.domain.http.payloads.generic;

import java.io.Serializable;

public interface EvGenericRequest<T> extends Serializable {
    void setUsername(String username);
    String getUsername();
}
