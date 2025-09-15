package com.abm.ice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EvolveEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvolveEurekaApplication.class, args);
    }

}
