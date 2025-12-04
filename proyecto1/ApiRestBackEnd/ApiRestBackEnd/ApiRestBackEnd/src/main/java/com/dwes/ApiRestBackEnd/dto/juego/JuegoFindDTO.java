package com.dwes.ApiRestBackEnd.dto.juego;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JuegoFindDTO {
    private String nombre;
    private String descripcion;
    private String reglas;
    private List<CriaturaFindDTO> criatura;
    private List<LugarDTO> lugar;

    public JuegoFindDTO(String nombre, String descripcion, String reglas,
                        List<CriaturaFindDTO> criatura, List<LugarDTO> lugar){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.reglas=reglas;
        this.criatura=criatura;
        this.lugar=lugar;
    }
}
