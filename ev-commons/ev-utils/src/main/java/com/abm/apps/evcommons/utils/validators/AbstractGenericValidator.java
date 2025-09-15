package com.abm.apps.evcommons.utils.validators;

import com.abm.apps.evcommons.utils.exceptions.EvValidationException;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public abstract class AbstractGenericValidator {

    protected Validator validator;

    @PostConstruct
    private void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> void runGenericValidations(T toValidate) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(toValidate);
        checkAndThrowForErrors(constraintViolations);
    }

    private <T> void checkAndThrowForErrors(Set<ConstraintViolation<T>> validationResults) {
        if (!CollectionUtils.isEmpty(validationResults)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Validation Exceptions encountered : ");
            validationResults.forEach(
                    it -> {
                        sb.append(String.format("[Class : %s , property : %s , message : %s] ; ", it.getRootBeanClass(), it.getPropertyPath(), it.getMessage()));
                    }
            );
            throw new EvValidationException(sb.toString());
        }
    }

}
