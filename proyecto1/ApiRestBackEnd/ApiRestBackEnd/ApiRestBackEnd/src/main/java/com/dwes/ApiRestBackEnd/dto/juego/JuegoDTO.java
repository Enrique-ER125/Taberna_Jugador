package com.dwes.ApiRestBackEnd.dto.juego;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class JuegoDTO {
    private String nombre;
    private String descripcion;
    private String reglas;

    public JuegoDTO(String nombre,String descripcion,String reglas){
        this.descripcion=descripcion;
        this.nombre=nombre;
        this.reglas=reglas;
    }
    public JuegoDTO(String nombre){
        this.nombre=nombre;
    }
}
