package com.abm.apps.im.domain.nodes;

import lombok.Data;

import java.util.List;

@Data
public class ContentNode {
    private String id;
    private String refName;
    private List<ContentNode> childNodes;
}
