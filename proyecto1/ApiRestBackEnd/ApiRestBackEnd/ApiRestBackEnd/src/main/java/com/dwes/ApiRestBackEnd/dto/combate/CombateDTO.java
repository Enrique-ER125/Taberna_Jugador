package com.dwes.ApiRestBackEnd.dto.combate;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CombateDTO {
    private String nombreLugar;
    private List<CriaturaFindDTO> criaturas;
    private CriaturaFindDTO boss;

    public CombateDTO(String nombreLugar, List<CriaturaFindDTO> criaturas, CriaturaFindDTO boss) {
        this.nombreLugar = nombreLugar;
        this.criaturas = criaturas;
        this.boss = boss;
    }
}
