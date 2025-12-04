package com.dwes.ApiRestBackEnd.dto.hechizos;

import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HechizoFindDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String elemento;
    private int ataque_fisico;
    private int salud;
    private int ataque_magico;
    private int mana;
    private List<JuegoDTO> juego;

    public HechizoFindDTO(String nombre, String descripcion, List<JuegoDTO> juego,
                            int ataque_fisico, int salud, int ataque_magico, int mana){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.juego=juego;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
    }


}
