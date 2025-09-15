package com.abm.apps.evcommons.beans.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ContentNode implements Serializable {

    private String label;
    private String refName;
    private List<ContentNode> contentNodes = new ArrayList<>();

}
