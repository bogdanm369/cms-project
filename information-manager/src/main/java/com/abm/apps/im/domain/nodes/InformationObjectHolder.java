package com.abm.apps.im.domain.nodes;

import com.abm.apps.im.domain.InformationObject;
import lombok.Data;

import java.util.List;

@Data
public class InformationObjectHolder {
    private InformationObject informationObject;
    private List<InformationObjectHolder> childNodes;
}
