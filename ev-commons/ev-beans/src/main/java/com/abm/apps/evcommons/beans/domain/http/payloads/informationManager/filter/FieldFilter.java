package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter;

import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.types.FieldTypes;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.types.ValueFilterTypes;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class FieldFilter implements Serializable {
    @NotNull
    private FieldTypes fieldToFilter;
    @NotNull
    private ValueFilterTypes filterByType;
    private String valueToFilter;
    private List<String> valuesToFilter;
    private boolean ignoreCase;
}
