package com.micros.motos.controller;

import com.micros.motos.entity.Moto;
import com.micros.motos.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/motos")
public class MotoController {

    @Autowired
    private MotoRepository MotoRepository;

    @GetMapping
    public ResponseEntity<List<Moto>> getMotos() {
        return ResponseEntity.ok(MotoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMotoPorId( @PathVariable("id") String idMoto ) {
        return ResponseEntity.ok(MotoRepository.findById(idMoto).get());
    }

    @PostMapping
    public ResponseEntity<Moto> guardaMoto( @RequestBody Moto Moto ) {
        return ResponseEntity.ok(MotoRepository.save(Moto));
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotosPorIdUsuario( @PathVariable String usuarioId ) {
        return ResponseEntity.ok(MotoRepository.findByUsuarioId(usuarioId));
    }


    private void metodo(String prueba){
        System.out.println(prueba);
    }

    public void metodo(String prueba, String prueba2){
        System.out.println(prueba);
    }

}
