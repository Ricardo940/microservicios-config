package com.usuario.service.feignclients;

import com.usuario.service.model.Carro;
import com.usuario.service.model.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service",path = "/carros",url = "http://localhost:8002")
public interface CarroFeignClient {

    @PostMapping()
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable(name = "usuarioId") int usuarioId);
}
