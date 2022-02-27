package com.micros.motos.repository;

import com.micros.motos.entity.Moto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MotoRepository extends MongoRepository<Moto, String> {
    List<Moto> findByUsuarioId(String usuarioId);
}
