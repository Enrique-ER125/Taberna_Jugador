package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.repository.CriaturaHechizoRepository;
import com.dwes.ApiRestBackEnd.repository.HechizoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HechizoService {
    private  final HechizoRepository hechizoRepository;
    private final CriaturaHechizoRepository criaturaHechizoRepository;

    public HechizoService(HechizoRepository hechizoRepository, CriaturaHechizoRepository criaturaHechizoRepository) {
        this.hechizoRepository = hechizoRepository;
        this.criaturaHechizoRepository = criaturaHechizoRepository;
    }

    public List<HechizoFindDTO> findAll() {
       List<Hechizo> hechizos = hechizoRepository.findHechizoAll();
        List<HechizoFindDTO> resultado = new ArrayList<>();
        List<JuegoDTO> juegoDTOS=new ArrayList<>();

        for (Hechizo hechizo : hechizos) {
            Long id = hechizo.getId();
            String nombre = hechizo.getNombre();
            String descripcion = hechizo.getDescripcion();
            int ataque_fisico = hechizo.getAtaque_fisico();
            int salud = hechizo.getSalud();
            int ataque_magico = hechizo.getAtaque_magico();
            int mana = hechizo.getMana();

            List<Juego> juegos = hechizoRepository.obtenerJuegodeHechizo(id);

            for (Juego juego : juegos) {
                String nombreJuego = juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }
            resultado.add(new HechizoFindDTO(nombre,descripcion,juegoDTOS,ataque_fisico,  salud,  ataque_magico,  mana));
        }
        return resultado;
    }

    public Hechizo save(Hechizo hechizo) {
        return hechizoRepository.save(hechizo);
    }

    public Hechizo update(Hechizo hechizo,Long idHechizo){
        Hechizo hechizoDB= hechizoRepository.findById(idHechizo).get();
        if(Objects.nonNull(hechizo.getNombre())&&!"".equalsIgnoreCase(hechizo.getNombre())){
            hechizoDB.setNombre(hechizo.getNombre());
        }
        if(Objects.nonNull(hechizo.getDescripcion())&&!"".equalsIgnoreCase(hechizo.getDescripcion())){
            hechizoDB.setDescripcion(hechizo.getDescripcion());
        }
        if(Objects.nonNull(hechizo.getTipo())&&!"".equalsIgnoreCase(hechizo.getTipo())){
            hechizoDB.setTipo(hechizo.getTipo());
        }
        if(Objects.nonNull(hechizo.getElemento())&&!"".equalsIgnoreCase(hechizo.getElemento())){
            hechizoDB.setElemento(hechizo.getElemento());
        }
        if(Objects.nonNull(hechizo.getAtaque_fisico())){
            hechizoDB.setAtaque_fisico(hechizo.getAtaque_fisico());
        }
        if(Objects.nonNull(hechizo.getAtaque_magico())){
            hechizoDB.setAtaque_magico(hechizo.getAtaque_magico());
        }
        if(Objects.nonNull(hechizo.getSalud())){
            hechizoDB.setSalud(hechizo.getSalud());
        }
        if(Objects.nonNull(hechizo.getMana())){
            hechizoDB.setMana(hechizo.getMana());
        }
        return hechizoRepository.save(hechizoDB);
    }

    @Transactional
    public void deleteById(Long idHechizo){
        criaturaHechizoRepository.deleteByHechizoId(idHechizo);
        hechizoRepository.deleteById(idHechizo);
    }
}
