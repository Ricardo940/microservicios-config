package com.moto.service.contlroller;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos(){
        List<Moto> motos = motoService.getAll();
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosByUsuario(@PathVariable int usuarioId){
        List<Moto> motos = motoService.byUsuarioId(usuarioId);
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable int id){
        Moto moto = motoService.getMotoById(id);

        if(moto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> guardarCarro(@RequestBody Moto moto){
        Moto motoBBDD = motoService.save(moto);
        return ResponseEntity.ok(motoBBDD);
    }
}
