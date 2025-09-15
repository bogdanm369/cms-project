package com.abm.apps.im.web.assemblers;

import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.InformationObjectDTO;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.utils.validators.InformationObjectValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class InformationDTOAssembler implements GenericResourceAssembler<InformationObject, InformationObjectDTO> {

    @Override
    public InformationObjectDTO toModel(InformationObject document) {

        InformationObjectDTO dto = InformationObjectDTO.builder()
                .id(document.getId())
                .refName(document.getRefName())
                .name(document.getName())
                .informationType(document.getInformationType())
                .dataFormat(document.getDataFormat())
                .contentName(document.getContentName())
                .creationDate(document.getCreationDate())
                .updatedDate(document.getUpdatedDate())
                .description(document.getDescription())
                .textContent(document.getTextContent())
                .binaryContent(document.getBinaryContent() != null ? new String( Base64.getEncoder().encode(document.getBinaryContent().getData()), StandardCharsets.UTF_8) : null)
                .build();

        return dto;
    }

    @Override
    public List<InformationObjectDTO> toModels(List<InformationObject> domainList) {

        List<InformationObjectDTO> dtoList = new ArrayList<>();

        domainList.forEach( it -> dtoList.add(toModel(it)));

        return dtoList;
    }


}
