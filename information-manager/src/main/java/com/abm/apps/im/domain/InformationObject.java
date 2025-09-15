package com.abm.apps.im.domain;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * Even for Single Relation and Tags, the actual Tag/Relation value will be kept in one item of the List
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("information-object-collection")
@CompoundIndexes({
        @CompoundIndex(name = "main_information", def = "{'mainInformation.name' : 1, 'mainInformation.informationType': 1}")
})
public class InformationObject {
    @Id
    private String id;
    @NotNull
    @Indexed(unique = true)
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
    private Binary binaryContent;
    @CreatedDate
    private Date creationDate;
    @LastModifiedDate
    private Date updatedDate;
}

