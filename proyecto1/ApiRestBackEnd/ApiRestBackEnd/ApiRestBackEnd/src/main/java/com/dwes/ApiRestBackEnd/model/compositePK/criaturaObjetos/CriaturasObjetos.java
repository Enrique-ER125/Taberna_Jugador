package com.dwes.ApiRestBackEnd.model.compositePK.criaturaObjetos;

import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Objeto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "criaturas_objetos")
public class CriaturasObjetos {
    @EmbeddedId
    private CriaturasObjetosId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_criaturas")
    @JoinColumn(name = "id_criaturas")
    private Criaturas criaturas;

    @ManyToOne
    @MapsId("id_objetos")
    @JoinColumn(name = "id_objetos")
    private Objeto objeto;
}
