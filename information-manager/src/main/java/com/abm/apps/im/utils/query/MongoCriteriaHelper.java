package com.abm.apps.im.utils.query;

import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import com.abm.apps.evcommons.beans.domain.http.payloads.informationManager.filter.InformationFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoCriteriaHelper {


    public void createMongoDbCriteria(InformationFilter informationFilter) {

        Query mongoQuery = new Query();

        obtainMainMetaInformation(informationFilter);


        mongoQuery.addCriteria(Criteria.where(""));
    }


    private List<InformationObject> obtainMainMetaInformation(InformationFilter informationFilter) {
        return obtainMetaInformation(informationFilter, null);
    }

    private List<InformationObject> obtainMetaInformation(InformationFilter informationFilter, InformationType informationType) {

        List<InformationObject> informationObjects = null;

       /* if(CollectionUtils.isEmpty(metaInformations) || ( uniqueResult != (metaInformations.size() == 1))) {
            throw new InformationValidationException(String.format("MetaInformation result list size not as expected : %s, unique result : %s", metaInformations.size(), uniqueResult), ExceptionTypes.VALIDATION);
        }*/

        return informationObjects;
    }

}
