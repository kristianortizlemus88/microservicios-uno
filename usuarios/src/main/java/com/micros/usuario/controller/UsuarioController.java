package com.micros.usuario.controller;

import com.micros.usuario.clientes.AutoCliente;
import com.micros.usuario.clientes.MotoCliente;
import com.micros.usuario.entity.Usuario;
import com.micros.usuario.model.Auto;
import com.micros.usuario.model.Moto;
import com.micros.usuario.repository.UsuarioRepository;
import com.micros.usuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutoCliente autoCliente;

    @Autowired
    private MotoCliente motoCliente;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioPorId( @PathVariable("id") String idUsuario ) {
        return ResponseEntity.ok(usuarioRepository.findById(idUsuario).get());
    }

    @PostMapping
    public ResponseEntity<Usuario> guardaUsuario( @RequestBody Usuario usuario ) {
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @CircuitBreaker(name = "autosCB", fallbackMethod = "getAutosFallBack")
    @GetMapping("/{id}/autos")
    public ResponseEntity<List<Auto>> getAutos(@PathVariable("id") String idUsuario ){
        List<Auto> autos = usuarioService.getAutos(idUsuario);
        return ResponseEntity.ok(autos);
    }

    @CircuitBreaker(name = "autosCB", fallbackMethod = "guardarAutoFallBack")
    @PostMapping("/{id}/autos")
    public ResponseEntity<Auto> guardarAuto(@PathVariable("id") String idUsuario, @RequestBody Auto auto ){
        auto.setUsuarioId(idUsuario);
        Auto autoGuardado = autoCliente.guardarAuto(auto);
        return ResponseEntity.ok(autoGuardado);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "getMotosFallBack")
    @GetMapping("/{id}/motos")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("id") String idUsuario ){
        List<Moto> motos = usuarioService.getMotos(idUsuario);
        return ResponseEntity.ok(motos);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "guardarMotosFallBack")
    @PostMapping("/{id}/motos")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("id") String idUsuario, @RequestBody Moto moto ){
        moto.setUsuarioId(idUsuario);
        Moto motoGuardada = motoCliente.guardarMoto(moto);
        return ResponseEntity.ok(motoGuardada);
    }

    @CircuitBreaker(name = "vehiculosCB", fallbackMethod = "getVehiculosFallBack")
    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<Map<String, Object>> getVehiculos(@PathVariable("id") String idUsuario ){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List<Auto> autos = autoCliente.getAutosPorUsuarioId(idUsuario);
        List<Moto> motos = motoCliente.getMotosPorUsuarioId(idUsuario);
        Map <String, Object> resultado = new HashMap<>();
        resultado.put("Usuario", usuario);
        resultado.put("Autos", autos);
        resultado.put("Motos", motos);
        return ResponseEntity.ok(resultado);
    }

    private ResponseEntity<List<Auto>> getAutosFallBack(@PathVariable("id") String idUsuario, RuntimeException ex ){
        return new ResponseEntity("El usuario " + idUsuario + " tiene los autos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Auto> guardarAutoFallBack(@PathVariable("id") String idUsuario, @RequestBody Auto auto, RuntimeException ex ){
        return new ResponseEntity("El usuario " + idUsuario + " no puede comprar autos en este momento", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>>  getMotosFallBack(@PathVariable("id") String idUsuario, RuntimeException ex ){
        return new ResponseEntity("El usuario " + idUsuario + " tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Moto> guardarMotosFallBack(@PathVariable("id") String idUsuario, @RequestBody Moto moto, RuntimeException ex ){
        return new ResponseEntity("El usuario " + idUsuario + " no puede comprar motos en este momento", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> getVehiculosFallBack(@PathVariable("id") String idUsuario, RuntimeException ex ){
        return new ResponseEntity("El usuario " + idUsuario + " tiene los vehiculos en el taller", HttpStatus.OK);
    }


}
