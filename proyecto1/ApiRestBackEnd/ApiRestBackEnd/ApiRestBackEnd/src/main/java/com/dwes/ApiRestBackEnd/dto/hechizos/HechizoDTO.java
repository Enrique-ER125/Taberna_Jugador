package com.dwes.ApiRestBackEnd.dto.hechizos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Builder
@Getter
@Setter
public class HechizoDTO {
    private String nombre;
    private String descripcion;
    private String tipo;
    private String elemento;
    private int ataque_fisico;
    private int salud;
    private int ataque_magico;
    private int mana;

    public HechizoDTO( String nombre,String descripcion,String tipo,String elemento,int ataque_fisico,int salud,int ataque_magico, int mana){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.tipo=tipo;
        this.elemento=elemento;
        this.ataque_fisico=ataque_fisico;
        this.salud=salud;
        this.ataque_magico=ataque_magico;
        this.mana=mana;
    }
}
