package com.abm.apps.im.validators;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import com.abm.apps.evcommons.utils.exceptions.EvValidationException;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.utils.validators.InformationObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.constants.InformationConstants.RELATION_LIST;
import static com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.constants.InformationConstants.TAG_LIST;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ValidatorTest {

    @Autowired
    private InformationObjectValidator iov;

    //@Test
    public void testForDescriptorOnly() {
        InformationObject informationObject =
                buildDescriptorOnlyInformationObject(
                        "descriptor-only", "This is the InformationObjectWrapper");

        assertDoesNotThrow(
                () -> {
                    iov.validateForAll(informationObject);
                });
    }

    @Test
    public void testForValueStored() {

        InformationObject valueStoredInformationObject =
                buildValueStoredInformationObject("value-stored-name");

        assertDoesNotThrow(
                () -> {
                    iov.validateForAll(valueStoredInformationObject);
                });
    }

    @Test
    public void testForRelation() {

        InformationObject relationInformationObject =
                buildRelationInformationObject("relation-information-object", "relation-value-1");

        assertDoesNotThrow(
                () -> {
                    iov.validateForAll(relationInformationObject);
                });
    }

    @Test
    public void testForTag() {

        InformationObject tagInformationObject =
                buildTagInformationObject("tag-information-object", "tag-value-1");

        assertDoesNotThrow(
                () -> {
                    iov.validateForAll(tagInformationObject);
                });
    }

    @Test
    public void testForRequiredFieldsWhichAreNull() {

        InformationObject informationObject =
                buildDescriptorOnlyInformationObject(
                        "descriptor-only", "This is the InformationObjectWrapper");
        InformationObject valueStoredInformationObject =
                buildValueStoredInformationObject("information-name");

        assertThrows(
                EvValidationException.class,
                () -> {
                    iov.validateForAll(informationObject);
                });
    }

    private InformationObject buildDescriptorOnlyInformationObject(
            String informationName, String descriptionText) {

        InformationObject wrapperInformationObject = new InformationObject();
        wrapperInformationObject.setName(informationName);
        wrapperInformationObject.setDescription(descriptionText);

        return wrapperInformationObject;
    }

    private InformationObject buildValueStoredInformationObject(String informationName) {
        return buildInformationObject(informationName, InformationType.VALUE_STORED, null);
    }

    private InformationObject buildRelationInformationObject(
            String informationName, String relationValue) {
        return buildInformationObject(informationName, InformationType.RELATION, relationValue);
    }

    private InformationObject buildTagInformationObject(String informationName, String tagValue) {
        return buildInformationObject(informationName, InformationType.TAG, tagValue);
    }

    private InformationObject buildInformationObject(
            String informationName, InformationType informationType, String tagOrRelationValue) {

        InformationObject informationObject = new InformationObject();
        informationObject.setName(informationName);
        informationObject.setInformationType(informationType);
        informationObject.setDataFormat(DataFormat.TEXT);

        if (InformationType.VALUE_STORED.equals(informationType)) {
            informationObject.setTextContent("Some text content");
        } else if (InformationType.RELATION.equals(informationType)) {
            informationObject
                    .getMetas()
                    .put(RELATION_LIST,
                            new ArrayList<String>() {
                                {
                                    add("123");
                                }
                            });
        } else if (InformationType.TAG.equals(informationType)) {
            informationObject
                    .getMetas()
                    .put(TAG_LIST,
                            new ArrayList<String>() {
                                {
                                    add("123");
                                }
                            });
        }

        return informationObject;
    }

}
