package com.micros.motos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( value = "moto")
@Getter
@Setter
@NoArgsConstructor
public class Moto {
    @Id
    private String id;
    private String marca;
    private String modelo;
    private String usuarioId;
}
