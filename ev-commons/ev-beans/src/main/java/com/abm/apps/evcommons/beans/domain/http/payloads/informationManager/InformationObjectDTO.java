package com.abm.apps.evcommons.beans.domain.http.payloads.informationManager;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationObjectDTO {

    private String id;
    @NotNull
    private String refName;
    private Map<String, Object> metas = new HashMap<>();
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private InformationType informationType;
    @NotNull
    private DataFormat dataFormat;
    private String contentName;
    private String textContent;
    private String binaryContent;
    private Date creationDate;
    private Date updatedDate;
}
