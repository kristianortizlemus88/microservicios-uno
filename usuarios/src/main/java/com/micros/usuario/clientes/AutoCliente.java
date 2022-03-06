package com.micros.usuario.clientes;

import com.micros.usuario.model.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "auto-microservicio", path = "/v1/autos")
public interface AutoCliente {

    @PostMapping()
    Auto guardarAuto (@RequestBody Auto auto);

    @GetMapping("/usuarios/{usuarioId}")
    List<Auto> getAutosPorUsuarioId (@PathVariable String usuarioId);
}
