package com.dwes.ApiRestBackEnd.dto.criaturas;

import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Setter
@Getter
public class CriaturaHechizoDTO {
    private Criaturas criatura;
    private List<Hechizo> hechizos;

    public CriaturaHechizoDTO(Criaturas criatura, List<Hechizo> hechizos) {
        this.criatura = criatura;
        this.hechizos = hechizos;
    }
}
