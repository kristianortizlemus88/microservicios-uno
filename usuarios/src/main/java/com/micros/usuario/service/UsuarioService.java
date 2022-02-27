package com.micros.usuario.service;

import com.micros.usuario.model.Auto;
import com.micros.usuario.model.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Auto> getAutos( String idUsuario ){
        List<Auto> autos = restTemplate.getForObject("http://localhost:8081/v1/autos/usuarios/" + idUsuario, List.class);
        return autos;
    }

    public List<Moto> getMotos(String idUsuario ){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8082/v1/motos/usuarios/" + idUsuario, List.class);
        return motos;
    }
}
