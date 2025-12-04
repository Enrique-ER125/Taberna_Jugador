package com.dwes.ApiRestBackEnd.model.compositePK.criaturaLugares;

import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Lugar;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "criaturas_lugares")
public class CriaturasLugares {
    @EmbeddedId
    private CriaturasLugaresId id;

    @JsonBackReference
    @ManyToOne
    @MapsId("id_criaturas")
    @JoinColumn(name = "id_criaturas")
    private Criaturas criaturas;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_lugar")
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;
}
