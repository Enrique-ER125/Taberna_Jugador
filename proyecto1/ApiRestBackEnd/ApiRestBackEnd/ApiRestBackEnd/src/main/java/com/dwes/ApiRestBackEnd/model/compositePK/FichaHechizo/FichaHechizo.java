package com.dwes.ApiRestBackEnd.model.compositePK.FichaHechizo;

import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.FichaPersonaje;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ficha_hechizos")
public class FichaHechizo {
    @EmbeddedId
    private FichaHechizoId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_fichaPersonaje")
    @JoinColumn(name = "id_fichaPersonaje")
    private FichaPersonaje fichaPersonaje;

    @ManyToOne
    @MapsId("id_hechizo")
    @JoinColumn(name = "id_hechizo")
    private Hechizo hechizo;
}
