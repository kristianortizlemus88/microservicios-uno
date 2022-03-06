package com.micros.autos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AutosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutosApplication.class, args);
    }

}
