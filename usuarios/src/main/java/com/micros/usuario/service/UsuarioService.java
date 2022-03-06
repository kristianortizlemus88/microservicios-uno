package com.micros.usuario.service;

import com.micros.usuario.model.Auto;
import com.micros.usuario.model.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Auto> getAutos( String idUsuario ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
        ResponseEntity<List> autos = restTemplate.exchange("http://auto-microservicio/v1/autos/usuarios/" + idUsuario, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
        return autos.getBody();
    }

    public List<Moto> getMotos(String idUsuario ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
        ResponseEntity<List> motos = restTemplate.exchange("http://moto-microservicio/v1/motos/usuarios/" + idUsuario, HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
        return motos.getBody();
    }
}
