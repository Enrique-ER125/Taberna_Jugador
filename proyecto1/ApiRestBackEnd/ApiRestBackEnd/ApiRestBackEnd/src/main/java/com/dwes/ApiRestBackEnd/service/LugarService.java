package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarFindDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import com.dwes.ApiRestBackEnd.repository.JuegoRepository;
import com.dwes.ApiRestBackEnd.repository.LugarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LugarService {
    private final LugarRepository lugarRepository;
    private final CriaturasService criaturasService;
    private final JuegoRepository juegoRepository;
    public LugarService(LugarRepository lugarRepository, CriaturasService criaturasService, JuegoRepository juegoRepository) {
        this.lugarRepository = lugarRepository;
        this.criaturasService = criaturasService;
        this.juegoRepository = juegoRepository;
    }

    public Lugar save(LugarDTO dto){
        Lugar lugar=new Lugar();
        lugar.setNombre(dto.getNombre());
        lugar.setHistoria(dto.getHistoria());
        lugar.setDescripcion(dto.getDescripcion());
        Juego juego = juegoRepository.findById(dto.getJuegoId()).orElseThrow();
        lugar.setJuegos(juego);

        return lugarRepository.save(lugar);
    }

    public List<LugarFindDTO> findAll(){
        List<Lugar> lugar=lugarRepository.findLugarAll();
        List<LugarFindDTO> resultado= new ArrayList<>();
        List<CriaturaFindDTO> criaturaFindDTOS=new ArrayList<>();
        List<JuegoDTO> juegoDTOS=new ArrayList<>();
        for (Lugar lugars:lugar) {
            Long idLugar= lugars.getId();
            String nombre = lugars.getNombre();
            String historia = lugars.getHistoria();
            String descripcion = lugars.getDescripcion();
            List<Criaturas> criaturas = lugarRepository.obtenerCriaturasdeLugar(idLugar);
            for (Criaturas criatura : criaturas) {
                Long id = criatura.getId();
                criaturaFindDTOS.addAll(criaturasService.findAllById(id));
            }
            List<Juego> juegos = lugarRepository.obtenerJuegodeLugar(idLugar);
            for (Juego juego : juegos) {
                String nombreJuego = juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }
            resultado.add(new LugarFindDTO(idLugar,nombre, historia, descripcion, criaturaFindDTOS, juegoDTOS));
        }
        return resultado;
    }

    public List<LugarFindDTO> findAllById(Long idLugar){
        Lugar lugar=lugarRepository.findAllById(idLugar);
        List<LugarFindDTO> resultado= new ArrayList<>();
        List<CriaturaFindDTO> criaturaFindDTOS=new ArrayList<>();
        List<JuegoDTO> juegoDTOS=new ArrayList<>();
        String nombre= lugar.getNombre();
        String historia= lugar.getHistoria();
        String descripcion= lugar.getDescripcion();
        List<Criaturas> criaturas=lugarRepository.obtenerCriaturasdeLugar(idLugar);
        for (Criaturas criatura:criaturas){
            Long id= criatura.getId();
            criaturaFindDTOS.addAll(criaturasService.findAllById(id));
        }
        List<Juego> juegos =lugarRepository.obtenerJuegodeLugar(idLugar);
        for (Juego juego:juegos){
            String nombreJuego= juego.getNombre();
            juegoDTOS.add(new JuegoDTO(nombreJuego));
        }
        resultado.add(new LugarFindDTO(nombre,historia,descripcion,criaturaFindDTOS,juegoDTOS));

        return resultado;
    }

    @Transactional
    public void deleteById(Long idLugar) {
        Lugar lugars = lugarRepository.findById(idLugar)
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado"));

        lugars.getCriaturas().clear();

        if (lugars.getJuegos() != null) {
            Long idJuego = lugars.getJuegos().getId();
            if (idJuego != null) {
                Juego juego = juegoRepository.findById(idJuego)
                        .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado"));
                juego.getLugars().remove(lugars); // mejor quitar solo este lugar
            }
        }

        lugarRepository.delete(lugars);
    }
}
