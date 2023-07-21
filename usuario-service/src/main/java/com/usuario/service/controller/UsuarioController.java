package com.usuario.service.controller;

import com.usuario.service.entiity.Usuario;
import com.usuario.service.model.Carro;
import com.usuario.service.model.Moto;
import com.usuario.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id){
        Usuario usuario = usuarioService.getUsuarioById(id);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioBBDD = usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioBBDD);
    }

    @GetMapping("carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable int usuarioId, @RequestBody Carro carro){
        Carro carroInsert = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(carroInsert);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable int usuarioId, @RequestBody Moto moto){
        Moto motoInsert = usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(motoInsert);
    }

    @GetMapping("/vehiculos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> obtenerUsuarioyVehiculos(@PathVariable int usuarioId){
        Map<String, Object> result = usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(result);
    }


}
