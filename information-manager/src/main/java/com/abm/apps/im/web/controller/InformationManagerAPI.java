package com.abm.apps.im.web.controller;

import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.CreateUpdateInformationRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.GetInformationByFilterRequest;
import com.abm.apps.evcommons.utils.config.EvHttpCalls;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.services.impl.InformationServiceImpl;
import com.abm.apps.im.web.util.InformationResourceHelper;
import com.abm.apps.tools.flowtracer.logging.MethodLogging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abm.apps.evcommons.beans.domain.http.constants.EvHttpConstants.*;

@Slf4j
@RestController
public class InformationManagerAPI {

    @Autowired
    private InformationServiceImpl informationService;

    @Autowired
    private InformationResourceHelper informationResourceHelper;

    /***
     *
     * Start Administrator Operations
     *
     * - Create Node List
     * - Update Node List
     * - Create, Update, Delete Informations
     * - Search for Informations
     *
     */

    @MethodLogging
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_CREATE_INFORMATION)
    public ResponseEntity<?> createInformation(@RequestBody EvRequestWrapperImpl<CreateUpdateInformationRequest> informationRequest) {
        List<InformationObject> informationObjects = informationService.createInformations(informationResourceHelper.toDocuments(informationRequest.getRequest().getInformationObjects()));
        return informationResourceHelper.buildGetOkResponse(informationObjects);
    }

    @MethodLogging
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_UPDATE_INFORMATION)
    public ResponseEntity<?> updateInformation(@RequestBody EvRequestWrapperImpl<CreateUpdateInformationRequest> informationRequest) {
        List<InformationObject> informationObjects = informationService.updateInformations(informationResourceHelper.toDocuments(informationRequest.getRequest().getInformationObjects()));
        return informationResourceHelper.buildGetOkResponse(informationObjects);
    }

    @MethodLogging
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_DELETE_INFORMATION_BY_ID)
    public ResponseEntity<?> deleteInformationById(@RequestParam(INFORMATION_ID) String informationId) {
        informationService.deleteInformationById(informationId);
        return ResponseEntity.ok().build();
    }

    @MethodLogging
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_DELETE_INFORMATION_BY_REF)
    public ResponseEntity<?> deleteInformationByRef(@RequestParam(INFORMATION_REF_NAME) String refName) {
        informationService.deleteInformationById(refName);
        return ResponseEntity.ok().build();
    }

    @MethodLogging
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_DELETE_INFORMATION_BY_IDS)
    public ResponseEntity<?> deleteInformationsByIds(@RequestParam(INFORMATION_IDS) List<String> informationIds) {
        informationService.deleteInformationsByIds(informationIds);
        return ResponseEntity.ok().build();
    }

    @MethodLogging
    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_ID)
    public ResponseEntity<?> getInformationById(@RequestParam(INFORMATION_ID) String informationId) {
        return informationResourceHelper.buildGetOkResponse(informationService.findInformationById(informationId));
    }

    @MethodLogging
    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_REF)
    public ResponseEntity<?> getInformationByRefName(@RequestParam(INFORMATION_REF_NAME) String refName) {
        return informationResourceHelper.buildGetOkResponse(informationService.findInformationByReference(refName));
    }

    @MethodLogging
    @GetMapping(EvHttpCalls.INFORMATION_MANAGER_GET_CONTENT_BY_LIST)
    public ResponseEntity<?> getInformationByListName(@RequestParam(INFORMATION_LIST_NAME) String listName) {
        return null;
    }


    @MethodLogging
    @PostMapping(EvHttpCalls.INFORMATION_MANAGER_GET_INFORMATION_BY_FILTER)
    public ResponseEntity<?> getInformationsByFilter(@RequestBody EvRequestWrapperImpl<GetInformationByFilterRequest> informationFilterDTO) {
        return informationResourceHelper.buildGetOkResponse(informationService.findInformationsByFilter(informationFilterDTO.getRequest().getInformationFilter()));
    }

    @MethodLogging
    @ResponseBody
    @GetMapping("/echo")
    public String echoText() {
        return "Hello !";
    }
}
