package com.abm.apps.im.utils.helpers;

import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.utils.InformationExceptionHelper;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InformationObjectHelper {

    @Autowired
    private InformationExceptionHelper exceptionHelper;

    private String referenceIdTemplate = "%s_%s";

    public void encodeBase64(List<InformationObject> informationObjects) {
        if (!CollectionUtils.isEmpty(informationObjects)) {
            informationObjects.forEach(
                    it -> encodeBase64(it)
            );
        }
    }

    public void decodeBase64(List<InformationObject> informationObjects) {
        if (!CollectionUtils.isEmpty(informationObjects)) {
            informationObjects.forEach(it ->
                    decodeBase64(it)
            );
        }
    }

    public void encodeBase64(InformationObject informationObject) {
        if (StringUtils.hasText(informationObject.getTextContent())) {
            informationObject.setTextContent(new String(Base64.decode(informationObject.getTextContent())));
        }
    }

    public void decodeBase64(InformationObject informationObject) {
        if (StringUtils.hasText(informationObject.getTextContent())) {
            informationObject.setTextContent(new String(Base64.decode(informationObject.getTextContent())));
        }
    }

    public String refName(InformationObject informationObject) {

        if (informationObject == null) {
            exceptionHelper.throwValidationExceptionForNullField("informationObject");
        }

        if (StringUtils.isEmpty(informationObject.getName())
                || informationObject.getInformationType() == null) {
            exceptionHelper.throwValidationException(
                    String.format("One or more fields are null/empty; name = [%s], informationType = [%s]", informationObject.getName(), informationObject.getInformationType())
            );
        }

        return String.format(referenceIdTemplate, informationObject.getName(), informationObject.getInformationType());
    }

    public <T> List<T> getMetaList(InformationObject informationObject, String listName, T t) {

        if (informationObject == null) {
            exceptionHelper.throwValidationExceptionForNullField("informationObject");
        }

        if (CollectionUtils.isEmpty(informationObject.getMetas())) {
            return new ArrayList<>();
        }

        List<T> metaList = null;
        Object o = informationObject.getMetas().get(listName);

        if (o != null) {
            metaList = (List<T>) o;
        } else {
            return new ArrayList<T>();
        }

        if (!CollectionUtils.isEmpty(metaList)) {
            return metaList;
        } else {
            return new ArrayList<T>();
        }
    }
}
