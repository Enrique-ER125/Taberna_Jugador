package com.dwes.ApiRestBackEnd.dto.lugar;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LugarDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String historia;
    private Long juegoId;


    public LugarDTO(Long id,String nombre,String historia,String descripcion){
        this.id=id;
        this.nombre=nombre;
        this.historia=historia;
        this.descripcion=descripcion;
    }
    public LugarDTO(){

    }

    public LugarDTO(Long id,String nombre,String historia,String descripcion,Long juegoId){
        this.id=id;
        this.nombre=nombre;
        this.historia=historia;
        this.descripcion=descripcion;
        this.juegoId=juegoId;
    }

    public LugarDTO(String nombre,String historia,String descripcion){
        this.nombre=nombre;
        this.historia=historia;
        this.descripcion=descripcion;
    }
}
