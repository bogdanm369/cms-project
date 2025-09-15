package com.abm.apps.im.repository;

import com.abm.apps.im.domain.InformationObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationObjectRepository extends MongoRepository<InformationObject, String> {

    InformationObject findOneByRefName(String refName);
    void deleteAllByRefName(String refName);
    List<InformationObject> findAllContentsByName(String contentName, boolean ignoreCase);
    List<InformationObject> findByIdIn(List<String> ids);
    List<InformationObject> findByRefNameIn(List<String> refs);

}
