package com.dwes.ApiRestBackEnd.dto.lugar;

import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import org.springframework.stereotype.Component;

@Component
public class LugarMapper {
    public Lugar toEntity(LugarDTO dto, Juego juego) {
        Lugar lugar = new Lugar();
        lugar.setId(dto.getId());
        lugar.setNombre(dto.getNombre());
        lugar.setDescripcion(dto.getDescripcion());
        lugar.setHistoria(dto.getHistoria());


        lugar.setJuegos(juego);

        return lugar;
    }

    public LugarDTO toDTO(Lugar lugar) {
        LugarDTO dto = new LugarDTO();
        dto.setId(lugar.getId());
        dto.setNombre(lugar.getNombre());
        dto.setDescripcion(lugar.getDescripcion());
        dto.setHistoria(lugar.getHistoria());
        return dto;
    }
}
