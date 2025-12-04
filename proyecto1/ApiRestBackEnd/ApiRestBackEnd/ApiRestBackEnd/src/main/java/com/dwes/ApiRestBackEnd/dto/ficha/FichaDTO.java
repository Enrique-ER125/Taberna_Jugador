package com.dwes.ApiRestBackEnd.dto.ficha;

import com.dwes.ApiRestBackEnd.model.Estadisticas;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Objeto;
import com.dwes.ApiRestBackEnd.model.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


//@Builder
@Getter
@Setter
public class FichaDTO {
    private String nombre;
    private String descripcion;
    private String historia;
    private Long juegoId;
    private Long usuarioId;
    private int ataque_fisico;
    private int salud;
    private int ataque_magico;
    private int mana;
    private int nivel;
    private int experiencia;
    private Set<Long> hechizos;
    private String imagen;
   // private Set<Objeto> objetos;

    public FichaDTO (String nombre,String descripcion,String historia,int ataque_fisico,int salud,int ataque_magico,int mana,int nivel, int experiencia,String imagen){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.historia=historia;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
        this.nivel=nivel;
        this.experiencia=experiencia;
        this.imagen=imagen;
    }
    public FichaDTO(){}
    public FichaDTO (String nombre){
        this.nombre=nombre;
    }

    public FichaDTO (String nombre,String descripcion,String historia,int ataque_fisico,int salud,int ataque_magico,int mana,Long juegos,Long usuario){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.historia=historia;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
        this.juegoId=juegos;
        this.usuarioId=usuario;
    }
}
