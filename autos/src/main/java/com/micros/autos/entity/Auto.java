package com.micros.autos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( value = "auto")
@Getter
@Setter
@NoArgsConstructor
public class Auto {
    @Id
    private String id;
    private String marca;
    private String modelo;
    private String usuarioId;
}
