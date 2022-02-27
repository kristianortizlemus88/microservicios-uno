package com.micros.usuario.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( value = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String correo;
}
