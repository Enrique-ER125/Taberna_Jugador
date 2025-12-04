package com.dwes.ApiRestBackEnd.dto.grupo;

import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class GrupoDTO {
    private Long id;
    private String nombre;
    private List<Long> juegos = new ArrayList<>();
    private List<Long> usuarios = new ArrayList<>();

    public GrupoDTO (String nombre){
        this.nombre=nombre;
    }

    public GrupoDTO (String nombre,List<Long> juegos,List<Long> usuarios){
        this.nombre=nombre;
        this.juegos=juegos;
        this.usuarios=usuarios;
    }
    public GrupoDTO (){}

    public GrupoDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}
