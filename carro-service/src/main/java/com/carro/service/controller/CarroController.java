package com.carro.service.controller;

import com.carro.service.entity.Carro;
import com.carro.service.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros(){
        List<Carro> carros = carroService.getAll();
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosByUsuario(@PathVariable int usuarioId){
        List<Carro> carros = carroService.byUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarrp(@PathVariable int id){
        Carro carro = carroService.getCarroById(id);

        if(carro == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
        Carro carroBBDD = carroService.save(carro);
        return ResponseEntity.ok(carroBBDD);
    }
}
