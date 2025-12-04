package com.dwes.ApiRestBackEnd.model.compositePK.criaturaHechizo;

import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "criaturas_hechizos")
public class CriaturasHechizo {
    @EmbeddedId
    private CriaturasHechizoId id;

    @JsonBackReference
    @ManyToOne
    @MapsId("id_criaturas")
    @JoinColumn(name = "id_criaturas")
    private Criaturas criaturas;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_hechizo")
    @JoinColumn(name = "id_hechizo")
    private Hechizo hechizo;
}
