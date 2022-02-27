package com.micros.autos.controller;

import com.micros.autos.entity.Auto;
import com.micros.autos.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/autos")
public class AutoController {

    @Autowired
    AutoRepository AutoRepository;

    @GetMapping
    public ResponseEntity<List<Auto>> getAutos() {
        return ResponseEntity.ok(AutoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAutoPorId( @PathVariable("id") String idAuto ) {
        return ResponseEntity.ok(AutoRepository.findById(idAuto).get());
    }

    @PostMapping
    public ResponseEntity<Auto> guardaAuto( @RequestBody Auto Auto ) {
        return ResponseEntity.ok(AutoRepository.save(Auto));
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Auto>> getAutosPorIdUsuario( @PathVariable String usuarioId ) {
        return ResponseEntity.ok(AutoRepository.findByUsuarioId(usuarioId));
    }

}
