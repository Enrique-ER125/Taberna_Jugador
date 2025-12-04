package com.dwes.ApiRestBackEnd.dto.criaturas;

import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Setter
@Getter
public class CriaturaFindDTO {
    private Long id;
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
    private List<JuegoDTO> juego;
    private List<LugarDTO> lugars;
    private List<HechizoDTO> hechizos;

    public CriaturaFindDTO(String nombre,String descripcion,List<JuegoDTO> juego,List<HechizoDTO> hechizos, String historia,
                       int nivel,int rango,int ataque_fisico,int salud,int ataque_magico,int mana,String imagen,List<LugarDTO> lugars){
        this.nivel=nivel;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.juego=juego;
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



    public CriaturaFindDTO(List<CriaturaFindDTO> allById) {
    }

    public CriaturaFindDTO(Long id, String nombre, String descripcion, List<JuegoDTO> juego, List<HechizoDTO> hechizos, String historia, int nivel, int rango, int ataque_fisico, int salud, int ataque_magico, int mana, String imagen, List<LugarDTO> lugars) {
        this.id=id;
        this.nivel=nivel;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.juego=juego;
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

    public CriaturaFindDTO(){

    }

    public CriaturaFindDTO(Long id, String nombre, String descripcion, int ataqueFisico, int salud, int ataqueMagico, int mana, int nivel, int rango, String historia) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
