package com.abm.apps.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.abm.apps")
public class InformationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationManagerApplication.class, args);
    }

}
