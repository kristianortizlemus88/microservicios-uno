package com.micros.autos.repository;

import com.micros.autos.entity.Auto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AutoRepository extends MongoRepository<Auto, String> {
    List<Auto> findByUsuarioId(String usuarioId);
}
