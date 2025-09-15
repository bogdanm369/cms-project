package com.abm.apps.im.web.converters;

import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.InformationObjectDTO;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.utils.validators.InformationObjectValidator;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class InformationObjectConverter implements GenericDocumentConverter<InformationObjectDTO, InformationObject> {

    @Override
    public InformationObject toDocument(InformationObjectDTO dto) {

        InformationObject io = InformationObject.builder()
                                .id(dto.getId())
                                .informationType(dto.getInformationType())
                                .refName(dto.getRefName())
                                .dataFormat(dto.getDataFormat())
                                .description(dto.getDescription())
                                .metas(dto.getMetas())
                                .textContent(dto.getTextContent())
                                .binaryContent(StringUtils.hasText(dto.getBinaryContent()) ? new Binary(Base64.getDecoder().decode(dto.getBinaryContent().getBytes(StandardCharsets.UTF_8))) : null)
                                .contentName(dto.getContentName())
                                .name(dto.getName())
                                .build();

        return io;
    }

    @Override
    public List<InformationObject> toDocuments(List<InformationObjectDTO> dtoList) {

        List<InformationObject> informationObjectList = new ArrayList<>();

        dtoList.forEach(
                it -> informationObjectList.add(toDocument(it))
        );

        return informationObjectList;
    }
}
