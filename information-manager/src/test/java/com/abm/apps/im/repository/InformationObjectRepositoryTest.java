package com.abm.apps.im.repository;

import com.abm.apps.evcommons.beans.domain.http.contentEnums.DataFormat;
import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.evcommons.beans.domain.http.enumtypes.InformationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class InformationObjectRepositoryTest {

    @Autowired
    private InformationObjectRepository informationObjectRepository;

    @Test
    public void testSaveInformation() {

        InformationObject informationObject =
                createMetaInformation(
                        "Monday Schedule",
                        "Money, Fun, Sleep",
                        DataFormat.TEXT,
                        InformationType.VALUE_STORED);

        InformationObject informationBean = createInformationBean(informationObject);

        informationObjectRepository.save(informationBean);

        List<InformationObject> informationBeans = informationObjectRepository.findAll();

        assertEquals(1, informationBeans.size());
    }

    private InformationObject createInformationBean(InformationObject mainInformationObject) {

        InformationObject informationBean = new InformationObject();

        return informationBean;
    }

    private InformationObject createMetaInformation(
            String name,
            String Value,
            DataFormat dataFormat,
            InformationType informationType) {

        InformationObject informationObject = new InformationObject();

        return informationObject;
    }
}
