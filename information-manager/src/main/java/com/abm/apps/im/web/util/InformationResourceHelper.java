package com.abm.apps.im.web.util;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.GetInformationByFilterResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.GetInformationResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.InformationObjectDTO;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.utils.InformationExceptionHelper;
import com.abm.apps.im.web.assemblers.InformationDTOAssembler;
import com.abm.apps.im.web.converters.InformationObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Component
public class InformationResourceHelper {


    @Autowired
    private InformationExceptionHelper exceptionHelper;

    @Autowired
    private InformationDTOAssembler informationDTOAssembler;

    @Autowired
    private InformationObjectConverter informationObjectConverter;


    public InformationObjectDTO toDTOs(InformationObject informationObject) {
        return informationDTOAssembler.toModel(informationObject);
    }

    public List<InformationObjectDTO> toDTOs(List<InformationObject> informationObjects) {
        return informationDTOAssembler.toModels(informationObjects);
    }

    public InformationObject toDocument(InformationObjectDTO informationObjectDTO) {
        return informationObjectConverter.toDocument(informationObjectDTO);
    }

    public List<InformationObject> toDocuments(List<InformationObjectDTO> informationObjectDTOs) {
        return informationObjectConverter.toDocuments(informationObjectDTOs);
    }



    public ResponseEntity<EvResponseWrapperImpl<GetInformationResponse>> buildGetOkResponse(InformationObject informationObject,
                                                                                            OperationResult operationResult,
                                                                                            String operationResultText) {

        if (null == informationObject) {
            exceptionHelper.throwValidationExceptionForNullField("informationObject");
        }

        EvResponseWrapperImpl<GetInformationResponse> response = new EvResponseWrapperImpl(GetInformationResponse
                                                                    .builder()
                                                                    .informationObjectDTO(informationDTOAssembler.toModel(informationObject))
                                                                    .operationResult(operationResult == null ? OperationResult.SUCCESS : operationResult)
                                                                    .operationResultText(StringUtils.hasText(operationResultText) ? operationResultText : "Success, nothing to report.")
                                                                    .build());


        return ResponseEntity.ok(response);
    }

    public ResponseEntity<EvResponseWrapperImpl<GetInformationResponse>> buildGetOkResponse(InformationObject informationObject) {
        return buildGetOkResponse(informationObject, null, null);
    }

    public ResponseEntity<EvResponseWrapperImpl<GetInformationByFilterResponse>> buildGetOkResponse(List<InformationObject> informationObjects,
                                                                                                    OperationResult operationResult,
                                                                                                    String operationResultText) {

        if (null == informationObjects) {
            exceptionHelper.throwValidationExceptionForNullField("informationObject");
        }

        EvResponseWrapperImpl<GetInformationByFilterResponse> response = new EvResponseWrapperImpl(GetInformationByFilterResponse
                .builder()
                .informationObjects(informationDTOAssembler.toModels(informationObjects))
                .operationResult(operationResult == null ? OperationResult.SUCCESS : operationResult)
                .operationResultText(StringUtils.hasText(operationResultText) ? operationResultText : "Success, nothing to report.")
                .build());


        return ResponseEntity.ok(response);
    }

    public ResponseEntity<EvResponseWrapperImpl<GetInformationByFilterResponse>> buildGetOkResponse(List<InformationObject> informationObject) {
        return buildGetOkResponse(informationObject, null, null);
    }

}
