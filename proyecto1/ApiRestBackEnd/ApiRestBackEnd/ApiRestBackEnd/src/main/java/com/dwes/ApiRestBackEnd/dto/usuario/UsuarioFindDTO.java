package com.dwes.ApiRestBackEnd.dto.usuario;

import com.dwes.ApiRestBackEnd.dto.ficha.FichaDTO;
import com.dwes.ApiRestBackEnd.dto.grupo.GrupoDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.model.Juego;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Builder
@Setter
@Getter
public class UsuarioFindDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String contrasena;
    private List<GrupoDTO> grupos;
    private List<JuegoDTO> juegos;
    private List<FichaDTO> fichaPersonajes;
    private String imagen;

    public UsuarioFindDTO(List<GrupoDTO> grupos){
        this.grupos=grupos;
    }

    public UsuarioFindDTO(Long id,String nombre, String descripcion, String contrasena, List<GrupoDTO> grupos, List<JuegoDTO> juegos, List<FichaDTO> fichaPersonajes,String imagen){
        this.id=id;
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.descripcion=descripcion;
        this.juegos=juegos;
        this.fichaPersonajes=fichaPersonajes;
        this.grupos=grupos;
        this.imagen=imagen;
    }
}
