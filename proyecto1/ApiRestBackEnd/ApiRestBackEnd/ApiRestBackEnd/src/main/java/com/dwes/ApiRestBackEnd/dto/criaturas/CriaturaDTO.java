package com.dwes.ApiRestBackEnd.dto.criaturas;

import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.model.Estadisticas;
import com.dwes.ApiRestBackEnd.model.FichaPersonaje;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Lugar;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

//@Builder
@Getter
@Setter
public class CriaturaDTO {
    private  Long id;
    private String nombre;
    private String descripcion;
    private int ataque_fisico;
    private int salud;
    private int ataque_magico;
    private int mana;
    private String imagen;
    private int nivel;
    private int rango;
    private String historia;
    private Long idJuego;
    private Set<Long> hechizos;
    private Set<Long> lugars;

    public CriaturaDTO(String nombre,String descripcion,Long idJuego,Set<Long> hechizos, String historia,Set<Long> lugars,
                       int nivel,int rango,int ataque_fisico,int salud,int ataque_magico,int mana,String imagen){
        this.nivel=nivel;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.idJuego=idJuego;
        this.hechizos=hechizos;
        this.historia=historia;
        this.rango=rango;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
        this.imagen=imagen;
        this.lugars=lugars;
    }
    public CriaturaDTO(){}

}
