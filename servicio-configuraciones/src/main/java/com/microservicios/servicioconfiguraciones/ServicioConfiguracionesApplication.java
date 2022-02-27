package com.microservicios.servicioconfiguraciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServicioConfiguracionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioConfiguracionesApplication.class, args);
    }

}
