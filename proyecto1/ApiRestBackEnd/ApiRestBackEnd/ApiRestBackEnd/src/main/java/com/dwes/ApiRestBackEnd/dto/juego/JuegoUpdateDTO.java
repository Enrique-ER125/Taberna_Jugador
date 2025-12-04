package com.dwes.ApiRestBackEnd.dto.juego;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Lugar;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JuegoUpdateDTO {
    private String nombre;
    private String descripcion;
    private String reglas;
    private Set<CriaturaFindDTO> criatura;
    private Set<LugarDTO> lugar;

    public JuegoUpdateDTO(String nombre, String descripcion, String reglas,
                          Set<CriaturaFindDTO> criatura, Set<LugarDTO> lugar){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.reglas=reglas;
        this.criatura=criatura;
        this.lugar=lugar;
    }
}
