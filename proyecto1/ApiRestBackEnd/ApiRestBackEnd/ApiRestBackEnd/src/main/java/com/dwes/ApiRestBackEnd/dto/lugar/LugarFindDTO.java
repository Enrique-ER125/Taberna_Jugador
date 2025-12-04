package com.dwes.ApiRestBackEnd.dto.lugar;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LugarFindDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String historia;
    private List<CriaturaFindDTO> criatura;
    private List<JuegoDTO> juegos;

    public LugarFindDTO (String nombre,String historia,String descripcion,List<CriaturaFindDTO> criatura,List<JuegoDTO> juegos){

        this.nombre=nombre;
        this.historia=historia;
        this.descripcion=descripcion;
        this.criatura=criatura;
        this.juegos=juegos;
    }

    public LugarFindDTO (Long id,String nombre,String historia,String descripcion,List<CriaturaFindDTO> criatura,List<JuegoDTO> juegos){
        this.id=id;
        this.nombre=nombre;
        this.historia=historia;
        this.descripcion=descripcion;
        this.criatura=criatura;
        this.juegos=juegos;
    }

    public LugarFindDTO(){}
}
