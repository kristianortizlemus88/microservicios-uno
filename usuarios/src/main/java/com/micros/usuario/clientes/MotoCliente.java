package com.micros.usuario.clientes;

import com.micros.usuario.model.Auto;
import com.micros.usuario.model.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-microservicio", path = "/v1/motos")
public interface MotoCliente {

    @PostMapping()
    Moto guardarMoto (@RequestBody Moto moto);

    @GetMapping("/usuarios/{usuarioId}")
    List<Moto> getMotosPorUsuarioId (@PathVariable String usuarioId);
}
