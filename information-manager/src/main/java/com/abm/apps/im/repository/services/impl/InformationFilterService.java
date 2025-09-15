package com.abm.apps.im.repository.services.impl;

import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.FieldFilter;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.InformationFilter;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.types.FieldTypes;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.types.ValueFilterTypes;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.utils.InformationExceptionHelper;
import com.abm.apps.im.utils.validators.InformationObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InformationFilterService {

    @Autowired
    private InformationRepositoryService informationRepositoryService;

    @Autowired
    private InformationObjectValidator informationObjectValidator;

    @Autowired
    private InformationExceptionHelper exceptionHelper;


    public List<InformationObject> filterByFilter(InformationFilter filter) {

        validateFilter(filter);

        List<InformationObject> searchList = new ArrayList<>();
        List<InformationObject> resultList = new ArrayList<>();

        List<FieldFilter> fieldFilters = filter.getFieldFilters();

        if (!CollectionUtils.isEmpty(filter.getInformationIds())) {
            searchList.addAll(informationRepositoryService.findAllByIds(filter.getInformationIds()));
        }

        if (!CollectionUtils.isEmpty(filter.getInformationRefs())) {
            searchList.addAll(informationRepositoryService.findAllByRefs(filter.getInformationRefs()));
        }


        if (!CollectionUtils.isEmpty(fieldFilters))
            fieldFilters.forEach(
                    it -> {
                        if (it.getFieldToFilter().equals(FieldTypes.NAME)) {
                            if (it.getFilterByType().equals(ValueFilterTypes.CONTAINS)) {
                                searchList.addAll(informationRepositoryService.findByNameContains(it.getValueToFilter(), it.isIgnoreCase()));
                            } else if (it.getFilterByType().equals(ValueFilterTypes.STARTS_WITH)) {
                                searchList.addAll(informationRepositoryService.findByNameStartsWith(it.getValueToFilter(), it.isIgnoreCase()));
                            } else if (it.getFilterByType().equals(ValueFilterTypes.ENDS_WITH)) {
                                searchList.addAll(informationRepositoryService.findByNameEndsWith(it.getValueToFilter(), it.isIgnoreCase()));
                            }
                        } else if (it.getFieldToFilter().equals(FieldTypes.META)) {
                            searchList.addAll(informationRepositoryService.findByMetas(it.getValuesToFilter()));
                        } else if (it.getFieldToFilter().equals(FieldTypes.DESCRIPTION)) {
                            if (it.getFilterByType().equals(ValueFilterTypes.CONTAINS)) {
                                searchList.addAll(informationRepositoryService.findByDescriptionContains(it.getValueToFilter(), it.isIgnoreCase()));
                            }
                        }
                    }
            );

        resultList.addAll(searchList);

        if (!CollectionUtils.isEmpty(filter.getDataFormats())) {
            resultList = resultList.stream().filter(it -> filter.getDataFormats().contains(it.getDataFormat())).collect(Collectors.toList());
        }

        if (!CollectionUtils.isEmpty(filter.getInformationTypes())) {
            resultList = searchList.stream().filter(it -> filter.getInformationTypes().contains(it.getInformationType())).collect(Collectors.toList());
        }

        return resultList;
    }

    private void validateFilter(InformationFilter filter) {

        if (CollectionUtils.isEmpty(filter.getFieldFilters())
                && CollectionUtils.isEmpty(filter.getDataFormats())
                && CollectionUtils.isEmpty(filter.getInformationTypes())
                && CollectionUtils.isEmpty(filter.getInformationIds())
                && CollectionUtils.isEmpty(filter.getInformationRefs())) {
            exceptionHelper.throwValidationException("InformationFilter filter objects cannot be all null.");
        }

        informationObjectValidator.runGenericValidations(filter);

        if (!CollectionUtils.isEmpty(filter.getFieldFilters()) && filter.getFieldFilters().size() > 1) {
            FieldFilter ff = filter.getFieldFilters().get(0);
            for (int i = 1; i < filter.getFieldFilters().size(); i++) {
                if (ff.getFieldToFilter().equals(filter.getFieldFilters().get(i))) {
                    exceptionHelper.throwValidationException(String.format("Found 2 FieldFilters for the same FieldType : %s; This will confuse filtering", ff.getFieldToFilter()));
                }
            }
        }
    }

}
