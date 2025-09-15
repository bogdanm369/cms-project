package com.abm.apps.evcommons.utils.validators;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EvObjectValidator extends AbstractGenericValidator
        implements EvDefaultValidator {


    public void genericValidateForAll(
            List<Object> objects, String fieldName) {

        checkNotNullOrEmpty(objects);
        objects.forEach(it -> genericValidateForAll(it, fieldName));
    }

    public void genericValidateForAll(
            List<Object> objects) {

        checkNotNullOrEmpty(objects);
        objects.forEach(it -> genericValidateForAll(it));
    }

    public void genericValidateForAll(Object object) {

        genericValidateForAll(object, "default-Field-Name");
    }

    public void genericValidateForAll(Object object, String fieldName) {

        checkNotNull(object, fieldName);

        runGenericValidations(object);
    }
}
