package com.abm.apps.tools.flowtracer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.abm.apps.tools.flowtracer")
public class FlowTracerConfig {
    @PostConstruct
    private void init(){
    }
}
