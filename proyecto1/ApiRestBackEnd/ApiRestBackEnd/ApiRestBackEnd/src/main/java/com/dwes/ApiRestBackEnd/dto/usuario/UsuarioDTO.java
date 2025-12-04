package com.dwes.ApiRestBackEnd.dto.usuario;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Builder
@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String contrasena;
    private List<Long> grupos = new ArrayList<>();
    private List<Long> juegos = new ArrayList<>();
    private List<Long> fichaPersonajes = new ArrayList<>();
    private String imagen;

    public UsuarioDTO(String nombre,String descripcion,String imagen){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
    }
    public UsuarioDTO(String nombre,String imagen){
        this.nombre=nombre;
        this.imagen=imagen;
    }
public UsuarioDTO(){

}

    public UsuarioDTO(String nombre,String descripcion,String contrasena,List<Long> grupos,List<Long> juegos,List<Long> fichaPersonajes,String imagen){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.contrasena=contrasena;
        this.grupos=grupos;
        this.juegos=juegos;
        this.fichaPersonajes=fichaPersonajes;
        this.imagen=imagen;
    }

    public UsuarioDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
