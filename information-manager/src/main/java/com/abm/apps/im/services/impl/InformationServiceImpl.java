package com.abm.apps.im.services.impl;

import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.InformationFilter;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.repository.services.impl.InformationFilterService;
import com.abm.apps.im.repository.services.impl.InformationRepositoryService;
import com.abm.apps.im.utils.InformationExceptionHelper;
import com.abm.apps.im.utils.helpers.InformationObjectHelper;
import com.abm.apps.im.utils.validators.InformationObjectValidator;
import com.abm.apps.tools.flowtracer.logging.MethodLogging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class InformationServiceImpl {

    @Autowired
    private InformationRepositoryService informationRepositoryService;
    @Autowired
    private InformationFilterService informationFilterService;
    @Autowired
    private InformationObjectHelper informationObjectHelper;
    @Autowired
    private InformationObjectValidator validator;
    @Autowired
    private InformationExceptionHelper exceptionHelper;

    public List<InformationObject> findAll() {
        return informationRepositoryService.findAll();
    }

    @MethodLogging
    public void createOrUpdateInformation(InformationObject information) {

        if (information == null) {
            exceptionHelper.throwValidationExceptionForNullField("information");
        }

        informationObjectHelper.decodeBase64(information);

        InformationObject io = informationRepositoryService.createOrUpdate(information);

        log.debug("CreateOrUpdateInformation result = [{}]", io);
    }


    public List<InformationObject> createInformations(List<InformationObject> informations) {
        return createOrUpdateInformations(informations, true);
    }

    public List<InformationObject> updateInformations(List<InformationObject> informations) {
        return createOrUpdateInformations(informations, false);
    }

    @MethodLogging
    private List<InformationObject> createOrUpdateInformations(List<InformationObject> informations, boolean isCreate) {

        if (CollectionUtils.isEmpty(informations)) {
            exceptionHelper.throwValidationExceptionForNullField("InformationObject list");
        }


        informations.forEach(
                item -> {
                    item.setRefName(informationObjectHelper.refName(item));
                    validator.validateForAll(item);
                    //informationObjectHelper.decodeBase64(item);
                    if (isCreate) {
                        validator.validateForCreate(item);
                    }
                    else {
                        validator.validateForUpdate(item);
                    }
                });

        List<InformationObject> result = informationRepositoryService.createOrUpdateInformations(informations);

        log.debug("createOrUpdateInformations result = [{}], isCreate = [{}]", result, isCreate);

        return result;
    }

    @MethodLogging
    public List<InformationObject> findInformationsByFilter(InformationFilter filter) {
        return informationFilterService.filterByFilter(filter);
    }

    @MethodLogging
    public List<InformationObject> findInformationByName(String name, boolean ignoreCase) {
        return informationRepositoryService.findByName(name, ignoreCase);
    }

    @MethodLogging
    public InformationObject findInformationById(String informationId) {
        if (!StringUtils.hasText(informationId)) {
            exceptionHelper.throwValidationExceptionForNullField(informationId);
        }
        InformationObject io = informationRepositoryService.findInformationById(informationId);
        log.debug("findInformationById result = [{}], informationId = [{}]", io, informationId);
        return io;
    }

    @MethodLogging
    public InformationObject findInformationByReference(String refName) {
        if (!StringUtils.hasText(refName)) {
            exceptionHelper.throwValidationExceptionForNullField(refName);
        }
        InformationObject io = informationRepositoryService.findOneByReference(refName);
        log.debug("findInformationByReference result = [{}], referenceId = [{}]", io, refName);
        return io;
    }

    @MethodLogging
    public void deleteInformationById(String id) {
        if (!StringUtils.hasText(id)) {
            exceptionHelper.throwValidationExceptionForNullField(id);
        }
        informationRepositoryService.deleteInformationById(id);
    }


    @MethodLogging
    public void deleteInformationByReference(String referenceId) {
        if (!StringUtils.hasText(referenceId)) {
            exceptionHelper.throwValidationExceptionForNullField(referenceId);
        }
        informationRepositoryService.deleteAllByReferenceId(referenceId);
    }


    @MethodLogging
    public void deleteInformationsByIds(List<String> entryIds) {
        if (CollectionUtils.isEmpty(entryIds)) {
            exceptionHelper.throwValidationException("entryIds");
        }
        entryIds.forEach(it -> {
            informationRepositoryService.deleteInformationById(it);
        });
    }

    @MethodLogging
    private void checkExistenceWithError(InformationObject informationObject) {
        if (StringUtils.hasText(informationObject.getId())) {
            exceptionHelper.throwValidationException(String.format("Id is null for Update operation; ref = %s, name = %s, description = %s", informationObject.getRefName(), informationObject.getName(), informationObject.getDescription()));
        }
        if (null == informationRepositoryService.findInformationById(informationObject.getId())) {
            exceptionHelper.throwValidationException(String.format("Could not find InformationObject by id %s", informationObject.getId()));
        }
    }
}
