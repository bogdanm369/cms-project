package com.abm.apps.evusbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = "com.abm.apps")
public class EvUsBffApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EvUsBffApplication.class, args);
    }

      @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EvUsBffApplication.class);
    }

}
