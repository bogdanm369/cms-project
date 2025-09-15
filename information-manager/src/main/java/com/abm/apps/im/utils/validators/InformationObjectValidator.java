package com.abm.apps.im.utils.validators;

import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapper;
import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import com.abm.apps.evcommons.utils.EvExceptionHelper;
import com.abm.apps.evcommons.utils.validators.EvObjectValidator;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.repository.InformationObjectRepository;
import com.abm.apps.im.utils.helpers.InformationObjectHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.constants.InformationConstants.RELATION_LIST;
import static com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.constants.InformationConstants.TAG_LIST;
import static com.abm.apps.im.utils.validators.ValidationMessages.fieldMustBeNullInformationType;
import static com.abm.apps.im.utils.validators.ValidationMessages.fieldMustNotBeNullInformationType;

@Component
@RequiredArgsConstructor
public class InformationObjectValidator extends EvObjectValidator {

    private final EvExceptionHelper evExceptionHelper;
    private final InformationObjectHelper informationObjectHelper;
    private final InformationObjectRepository informationObjectRepository;
    private final EvExceptionHelper exceptionHelper;


    public void validateRequest(EvRequestWrapper<? extends EvGenericRequest> requestPayload) {
        checkNotNull(requestPayload, "requestWrapper");
        checkNotNull(requestPayload.getRequest(), "requestWrapper.request");
    }

    public void validateForAll(
            List<InformationObject> informationBeans) {

        checkNotNullOrEmpty(informationBeans);
        informationBeans.forEach(it -> validateForAll(it));
    }

    public void validateForAll(InformationObject informationObject) {

        checkNotNull(informationObject, "informationBean");

        runGenericValidations(informationObject);

        validateForDescriptorType(informationObject);

        if (InformationType.VALUE_STORED.equals(informationObject.getInformationType())) {
            validateForValueStored(informationObject);
        } else if (InformationType.RELATION.equals(informationObject.getInformationType())) {
            validateForRelation(informationObject);
        } else if (InformationType.TAG.equals(informationObject.getInformationType())) {
            validateForTag(informationObject);
        }
    }

    private void validateForDescriptorType(InformationObject informationObject) {
        if (InformationType.WRAPPER.equals(informationObject.getInformationType())) {
            evExceptionHelper.throwValidationException(
                    String.format(fieldMustNotBeNullInformationType, "informationObjects", "Descriptor"));
        }
    }

    /**
     * Validates that all the required fields are correctly set for the business flow of storing a
     * Value ( either locally or Remote )
     *
     * @param informationObject
     */
    private void validateForValueStored(InformationObject informationObject) {

        if (!CollectionUtils.isEmpty(informationObjectHelper.getMetaList(informationObject, RELATION_LIST, String.class))
                || !CollectionUtils.isEmpty(informationObjectHelper.getMetaList(informationObject, TAG_LIST, String.class))) {
            evExceptionHelper.throwValidationException(
                    String.format(
                            fieldMustBeNullInformationType,
                            String.format("additionalMetas containing [%s] or [%s]",
                                    RELATION_LIST,
                                    TAG_LIST),
                            InformationType.VALUE_STORED));
        }
    }

    /**
     * Validates that all the required fields are correctly set for the business flow of storing a
     * Relation
     *
     * @param informationObject
     */
    private void validateForRelation(InformationObject informationObject) {

        if (CollectionUtils.isEmpty(informationObjectHelper.getMetaList(informationObject, RELATION_LIST, String.class))) {
            evExceptionHelper.throwValidationException(
                    String.format(fieldMustNotBeNullInformationType, "additionalMetas." + RELATION_LIST, InformationType.RELATION));
        }
    }

    /**
     * Validates that all the required fields are correctly set for the business flow of storing a Tag
     *
     * @param informationObject
     */
    private void validateForTag(InformationObject informationObject) {
        if (CollectionUtils.isEmpty(informationObjectHelper.getMetaList(informationObject, TAG_LIST, String.class))) {
            evExceptionHelper.throwValidationException(
                    String.format(fieldMustNotBeNullInformationType, "additionalMetas." + TAG_LIST, InformationType.TAG));
        }
    }

    public void validateForCreate(InformationObject informationObject) {
        if (StringUtils.hasText(informationObject.getId())) {
            exceptionHelper.throwValidationException(String.format("Id must be null for create operation; ref = %s, name = %s, description = %s", informationObject.getRefName(), informationObject.getName(), informationObject.getDescription()));
        }
        if(null != informationObjectRepository.findOneByRefName(informationObject.getRefName())) {
            exceptionHelper.throwValidationException(String.format("InformationObject with refName [%s] already exists in the database; name = %s, description = %s", informationObject.getRefName(), informationObject.getName(), informationObject.getDescription()));
        }
    }

    public void validateForUpdate(InformationObject informationObject) {
        if (!StringUtils.hasText(informationObject.getId())) {
            exceptionHelper.throwValidationException(String.format("Id cannot be null for create operation; ref = %s, name = %s, description = %s", informationObject.getRefName(), informationObject.getName(), informationObject.getDescription()));
        }
        if(null == informationObjectRepository.findById(informationObject.getId())) {
            exceptionHelper.throwValidationException(String.format("InformationObject with ID [%s] does not exist in the database; id = %s, name = %s, description = %s", informationObject.getRefName(), informationObject.getName(), informationObject.getDescription()));
        }
    }


}
