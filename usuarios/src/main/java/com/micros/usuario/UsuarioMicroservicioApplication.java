package com.micros.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsuarioMicroservicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioMicroservicioApplication.class, args);
    }

}
