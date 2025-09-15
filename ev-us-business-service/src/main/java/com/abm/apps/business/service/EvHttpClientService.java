package com.abm.apps.business.service;

import com.abm.apps.business.config.EvBusinessProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvHttpClientService {

    @Autowired
    private EvBusinessProperties evBusinessProperties;

}
