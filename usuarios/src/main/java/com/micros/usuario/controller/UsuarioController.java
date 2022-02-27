package com.micros.usuario.controller;

import com.micros.usuario.clientes.AutoCliente;
import com.micros.usuario.clientes.MotoCliente;
import com.micros.usuario.entity.Usuario;
import com.micros.usuario.model.Auto;
import com.micros.usuario.model.Moto;
import com.micros.usuario.repository.UsuarioRepository;
import com.micros.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}/autos")
    public ResponseEntity<List<Auto>> getAutos(@PathVariable("id") String idUsuario ){
        List<Auto> autos = usuarioService.getAutos(idUsuario);
        return ResponseEntity.ok(autos);
    }

    @GetMapping("/{id}/motos")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("id") String idUsuario ){
        List<Moto> motos = usuarioService.getMotos(idUsuario);
        return ResponseEntity.ok(motos);
    }

    @PostMapping("/{id}/autos")
    public ResponseEntity<Auto> guardarAuto(@PathVariable("id") String idUsuario, @RequestBody Auto auto ){
        auto.setUsuarioId(idUsuario);
        Auto autoGuardado = autoCliente.guardarAuto(auto);
        return ResponseEntity.ok(autoGuardado);
    }

    @PostMapping("/{id}/motos")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("id") String idUsuario, @RequestBody Moto moto ){
        moto.setUsuarioId(idUsuario);
        Moto motoGuardada = motoCliente.guardarMoto(moto);
        return ResponseEntity.ok(motoGuardada);
    }

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

}
