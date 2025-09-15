package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Filter POJO used to filter <code>InformationBean</code>s based on specified filtered data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationFilter implements Serializable {
    private List<String> informationIds;
    private List<String> informationRefs;
    private List<DataFormat> dataFormats;
    private List<InformationType> informationTypes;
    private List<FieldFilter> fieldFilters;
}
