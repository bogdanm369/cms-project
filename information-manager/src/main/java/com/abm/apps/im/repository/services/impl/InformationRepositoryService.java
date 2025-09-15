package com.abm.apps.im.repository.services.impl;

import com.abm.apps.im.domain.InformationObject;
import com.abm.apps.im.repository.InformationObjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.abm.apps.im.constants.repository.MongoRepoConstants.*;

@Slf4j
@Service
public class InformationRepositoryService {

    @Autowired
    private InformationObjectRepository informationObjectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<InformationObject> findAllByIds(List<String> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return informationObjectRepository.findByIdIn(ids);
    }

    public List<InformationObject> findAllByRefs(List<String> refs) {

        if (CollectionUtils.isEmpty(refs)) {
            return new ArrayList<>();
        }
        return informationObjectRepository.findByRefNameIn(refs);
    }

    public List<InformationObject> findByName(String contentName, boolean ignoreCase) {

        if (!StringUtils.isEmpty(contentName)) {
            Query query = new Query();
            query.addCriteria(Criteria.where("name").regex(contentName, ignoreCase ? IGNORE_CASE : ""));
            return mongoTemplate.find(query, InformationObject.class);
        }
        return Collections.emptyList();
    }

    public List<InformationObject> findByNameContains(String contains, boolean ignoreCase) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("name")
                        .regex(LIKE_ANY + contains + LIKE_ANY, ignoreCase ? IGNORE_CASE : ""));
        return mongoTemplate.find(query, InformationObject.class);
    }

    public List<InformationObject> findByNameStartsWith(String startsWith, boolean ignoreCase) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("name")
                        .regex(TRIANGLE_STARTS_WITH + startsWith, ignoreCase ? IGNORE_CASE : ""));
        return mongoTemplate.find(query, InformationObject.class);
    }

    public List<InformationObject> findByNameEndsWith(String endsWith, boolean ignoreCase) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("name").regex(endsWith + DOLLAR_ENDS_WITH, ignoreCase ? IGNORE_CASE : ""));
        return mongoTemplate.find(query, InformationObject.class);
    }

    public List<InformationObject> findByDescriptionContains(String contains, boolean ignoreCase) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("description")
                        .regex(LIKE_ANY + contains + LIKE_ANY, ignoreCase ? IGNORE_CASE : ""));
        return mongoTemplate.find(query, InformationObject.class);
    }

    public List<InformationObject> findByMetas(List<String> metaNames) {

        List<String> newList = new ArrayList();
        if (!CollectionUtils.isEmpty(metaNames)) {
            metaNames.forEach(i ->
                    newList.add(i.toUpperCase())
            );
        } else {
            log.info("findByMetas -> Empty meta names list supplied");
            return new ArrayList<>();
        }

        Query query = new Query();
        query.addCriteria(
                Criteria.where("additionalMetas.metaName").all(newList));
        return mongoTemplate.find(query, InformationObject.class);
    }


    public InformationObject createOrUpdate(InformationObject informationObject) {

        return informationObjectRepository.save(informationObject);
    }

    public List<InformationObject> createOrUpdateInformations(
            List<InformationObject> informationObjects) {

        return informationObjectRepository.saveAll(informationObjects);
    }

    public List<InformationObject> findAll() {
        return informationObjectRepository.findAll();
    }

    public InformationObject findOneByReference(String refName) {
        return informationObjectRepository.findOneByRefName(refName);
    }

    public void deleteAllByReferenceId(String referenceId) {
        informationObjectRepository.deleteAllByRefName(referenceId);
    }

    public void deleteInformationById(String id) {
        informationObjectRepository.deleteById(id);
    }

    public InformationObject findInformationById(String id) {
        return informationObjectRepository.findById(id).orElse(null);
    }

}
