package com.usuario.service.service;

import com.usuario.service.entiity.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.model.Carro;
import com.usuario.service.model.Moto;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private MotoFeignClient motoFeignClient;

    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro carroInsert = carroFeignClient.save(carro);
        return carroInsert;
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto motoInsert = motoFeignClient.save(moto);
        return motoInsert;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> result = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null){
            result.put("Messaje","El usuario no existe");
        }

        result.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if(!carros.isEmpty()){
            result.put("Carros", carros);
        }else {
            result.put("Carros", "El usuario no tiene carros");
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(!motos.isEmpty()){
            result.put("Motos", motos);
        }else {
            result.put("Motos", "El usuario no tiene motos");
        }

        return result;
    }

    public List<Carro> getCarrosFeign(int usuarioId){
        return carroFeignClient.getCarros(usuarioId);
    }

    public List<Moto> getMotosFeign(int usuarioId){
        return motoFeignClient.getMotos(usuarioId);
    }


    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carros/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/motos/usuario/" + usuarioId, List.class);
        return motos;
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario usuarioBBDD = usuarioRepository.save(usuario);
        return  usuarioBBDD;
    }
}
