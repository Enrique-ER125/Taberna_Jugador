package com.dwes.ApiRestBackEnd.dto.ficha;

import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioDTO;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Builder
@Setter
@Getter
public class FichaFindDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String historia;
    private  List<JuegoDTO> juegos;
    private  List<UsuarioDTO> usuarios;
    private int ataque_fisico;
    private int salud;
    private int nivel;
    private int ataque_magico;
    private int mana;
    private int experiencia;
    private String imagen;

    public FichaFindDTO (String nombre, String descripcion, String historia, int ataque_fisico, int salud, int ataque_magico, int mana,int nivel, int experiencia, List<JuegoDTO> juegos, List<UsuarioDTO> usuarios,String imagen){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.historia=historia;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
        this.nivel=nivel;
        this.experiencia=experiencia;
        this.juegos=juegos;
        this.usuarios=usuarios;
        this.imagen=imagen;
    }

    public FichaFindDTO (Long id,String nombre, String descripcion, String historia, int ataque_fisico, int salud, int ataque_magico, int mana,int nivel, int experiencia, List<JuegoDTO> juegos, List<UsuarioDTO> usuarios,String imagen){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.historia=historia;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
        this.nivel=nivel;
        this.experiencia=experiencia;
        this.juegos=juegos;
        this.usuarios=usuarios;
        this.imagen=imagen;
    }
}
