package com.abm.apps.im.web;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.CreateUpdateInformationRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.CreateUpdateInformationResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.InformationObjectDTO;
import com.abm.apps.evcommons.utils.config.EvHttpCalls;
import com.abm.apps.evcommons.utils.testUtils.EvTestUtils;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.repository.services.impl.InformationRepositoryService;
import com.abm.apps.im.services.impl.InformationServiceImpl;
import com.abm.apps.im.web.util.InformationResourceHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@AutoConfigureDataMongo()
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith({SpringExtension.class})
public class InformationAPITest {

    private String defaultName = "default-name";
    private String defaultContentName = defaultName + "--default-content-name";
    private String defaultDescription = "default-description";
    private String defaultValue = "defaultValue";

    private int port = 8080;

    private String baseUrl = "http://localhost:" + port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InformationRepositoryService informationRepositoryService;

    @Autowired
    private InformationServiceImpl informationService;

    @Autowired
    private InformationResourceHelper informationResourceHelper;




    private String serviceUrl(String subUrl) {
        return baseUrl + subUrl;
    }

    @Test
    public void testCreateInformation() {

        List<InformationObjectDTO> informationDTOs = informationResourceHelper.toDTOs(createValueInformationObject(defaultName, defaultDescription, defaultValue));
        EvRequestWrapperImpl<CreateUpdateInformationRequest> request = getRequestWrapper(getCreateUpdateRequest(informationDTOs));

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> responseEntity = restTemplate.exchange(serviceUrl(EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION),
                                                                                                HttpMethod.POST,
                                                                                                new HttpEntity<>(request), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {});

        List<InformationObject> informationObjects = informationRepositoryService.findAll();

        log.info(responseEntity.getBody().toString());
        log.info("Request content : [{}]", EvTestUtils.toJson(request));
        log.info("Content : [{}]", EvTestUtils.toJson(responseEntity.getBody().getResponse()));
        log.info(responseEntity.getStatusCode().toString());

        assertTrue(informationObjects.size() > 0);
    }

    @Test
    public void testUpdateInformation() {

        String updatedTextValue = "textValueUpdated";

        List<InformationObjectDTO> informationDTOs = informationResourceHelper.toDTOs(createValueInformationObject(defaultName, defaultDescription, defaultValue));
        EvRequestWrapperImpl<CreateUpdateInformationRequest> request = getRequestWrapper(getCreateUpdateRequest(informationDTOs));

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> responseEntity = restTemplate.exchange(serviceUrl(EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION),
                                                                                                HttpMethod.POST, new HttpEntity<>(request),
                                                                                                new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {});

        String createId = responseEntity.getBody().getResponse().getInformationObjects().get(0).getId();

        List<InformationObject> informationObjects = informationRepositoryService.findAll();

        List<InformationObjectDTO> updatedPayloadDTO = informationResourceHelper.toDTOs(createValueInformationObject(defaultName, defaultDescription, updatedTextValue));

        updatedPayloadDTO.get(0).setId(createId);

        EvRequestWrapperImpl<CreateUpdateInformationRequest> updateRequest = getRequestWrapper(getCreateUpdateRequest(updatedPayloadDTO));

        ResponseEntity<EvResponseWrapperImpl<CreateUpdateInformationResponse>> updatedEntity = restTemplate.exchange(serviceUrl(EvHttpCalls.INFORMATION_MANAGER_UPDATE_INFORMATION), HttpMethod.POST, new HttpEntity<>(updateRequest), new ParameterizedTypeReference<EvResponseWrapperImpl<CreateUpdateInformationResponse>>() {});

        log.info(responseEntity.getBody().toString());
        log.info(responseEntity.getStatusCode().toString());

        assertEquals(createId, updatedEntity.getBody().getResponse().getInformationObjects().get(0).getId());
        assertTrue(informationObjects.size() > 0);
        assertEquals(informationService.findInformationById(createId).getTextContent(), updatedTextValue);
        assertEquals(updatedTextValue, updatedEntity.getBody().getResponse().getInformationObjects().get(0).getTextContent());
    }

/*
    @Test
    public void testGetInformation() {

        InformationPayloadDTO payloadDTO = resourceAssembler.toModel(createValueInformationObject(defaultName, defaultDescription, defaultValue));

        ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl("/information/create"), HttpMethod.PUT, new HttpEntity<>(payloadDTO), String.class);

        List<InformationObject> informationObjects = informationRepositoryService.findAll();
        assertTrue(informationObjects.size() > 0);

        String getId = informationObjects.get(0).getId();

        ResponseEntity<InformationPayloadDTO> getResponseEntity = restTemplate.getForEntity(serviceUrl("/information/id/" + getId), InformationPayloadDTO.class);

        assertEquals(getId, getResponseEntity.getBody().getInformationObjects().get(0).getId());
    }

    @Test
    public void testCreateTextInformation() {

        String textContent = "some-text-value-field";

        InformationPayloadDTO payloadDTO = resourceAssembler.toModel(createValueInformationObject(defaultName, defaultDescription, textContent));

        ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl("/information/create"), HttpMethod.PUT, new HttpEntity<>(payloadDTO), String.class);

        List<InformationObject> informationObjects = informationRepositoryService.findAll();

        log.info(responseEntity.getBody());
        log.info(responseEntity.getStatusCode().toString());

        assertTrue(informationObjects.size() > 0);
        assertEquals(informationObjects.get(0).getTextContent(), textContent);
    }

    @Test
    public void testFilterByFilter() {

        InformationPayloadDTO payloadDTO = resourceAssembler.toModel(createValueInformationObject(defaultName, defaultDescription, defaultValue));
        payloadDTO.getInformationObjects().get(0).getAdditionalMetas().add(new MetaInformation("KEY-1", "META-VALUE-1"));
        payloadDTO.getInformationObjects().get(0).getMetas().put("key1", "123");
        InformationPayloadDTO payloadDTO2 = resourceAssembler.toModel(createValueInformationObject(defaultName, defaultDescription, defaultValue));
        payloadDTO2.getInformationObjects().get(0).getAdditionalMetas().add(new MetaInformation("KEY-1", "META-VALUE-2"));
        payloadDTO2.getInformationObjects().get(0).getMetas().put("key1", "123");

        InformationPayloadDTO payloadDTO3 = resourceAssembler.toModel(createValueInformationObject(defaultName, defaultDescription, defaultValue));
        payloadDTO3.getInformationObjects().get(0).getAdditionalMetas().add(new MetaInformation("KEY-2", "META-VALUE-3"));
        payloadDTO3.getInformationObjects().get(0).getMetas().put("key1", "123");

        restTemplate.exchange(serviceUrl("/information/create"), HttpMethod.PUT, new HttpEntity<>(payloadDTO), String.class);
        restTemplate.exchange(serviceUrl("/information/create"), HttpMethod.PUT, new HttpEntity<>(payloadDTO2), String.class);
        restTemplate.exchange(serviceUrl("/information/create"), HttpMethod.PUT, new HttpEntity<>(payloadDTO3), String.class);

        List<String> metaKeys = new ArrayList<>();
        metaKeys.add("KEY-1");

        InformationFilter filter = new InformationFilter();
        filter.setFieldFilters(new ArrayList<>());
        filter.getFieldFilters().add(FieldFilter.builder().fieldToFilter(FieldTypes.META).valuesToFilter(metaKeys).build());

        InformationFilterDTO filterDTO = new InformationFilterDTO();
        filterDTO.setInformationFilter(filter);

        ResponseEntity<InformationResponseDTO> responseDTO = restTemplate.exchange(serviceUrl("/information/filter"), HttpMethod.POST, new HttpEntity<>(filterDTO), InformationResponseDTO.class);

        assertNotNull(responseDTO.getBody());
        assertEquals(2, responseDTO.getBody().getInformationObjectIds().size());
    }

 */

    private List<InformationObject> createValueInformationObject(String name, String description, String textValue) {

        List<InformationObject> ioList = new ArrayList<>();
        InformationObject io = new InformationObject();

        io.setName(name);
        io.setContentName("content-name-" + name);
        io.setInformationType(InformationType.VALUE_STORED);
        io.setDescription(description);
        io.setTextContent(textValue);
        io.setDataFormat(DataFormat.TEXT);

        ioList.add(io);

        return ioList;
    }

    public CreateUpdateInformationRequest getCreateUpdateRequest(List<InformationObjectDTO> dtos) {
        return CreateUpdateInformationRequest.builder().informationObjects(dtos).build();
    }

    public <T extends EvGenericRequest> EvRequestWrapperImpl<T> getRequestWrapper(T request) {
        return (EvRequestWrapperImpl<T>) EvRequestWrapperImpl.builder().request(request).build();
    }

}
