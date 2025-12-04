package com.dwes.ApiRestBackEnd.dto.grupo;

import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioDTO;
import com.dwes.ApiRestBackEnd.model.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class GrupoFindDTO {
    private Long id;
    private final String nombre;
    private final List<JuegoDTO> juegos;
    private final List<UsuarioDTO> usuarios;

    public GrupoFindDTO(String nombre, List<JuegoDTO> juegos, List<UsuarioDTO> usuarios){
        this.nombre=nombre;
        this.juegos=juegos;
        this.usuarios=usuarios;
    }

    public GrupoFindDTO(Long id,String nombre, List<JuegoDTO> juegos, List<UsuarioDTO> usuarios){
        this.id=id;
        this.nombre=nombre;
        this.juegos=juegos;
        this.usuarios=usuarios;
    }
}
