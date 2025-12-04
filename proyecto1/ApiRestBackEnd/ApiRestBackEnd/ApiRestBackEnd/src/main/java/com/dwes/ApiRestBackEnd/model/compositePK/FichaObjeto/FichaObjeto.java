package com.dwes.ApiRestBackEnd.model.compositePK.FichaObjeto;


import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.FichaPersonaje;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Objeto;
import com.dwes.ApiRestBackEnd.model.compositePK.FichaHechizo.FichaHechizoId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ficha_objetos")
public class FichaObjeto  {
    @EmbeddedId
    private FichaObjetoId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_fichaPersonaje")
    @JoinColumn(name = "id_fichaPersonaje")
    private FichaPersonaje fichaPersonaje;

    @ManyToOne
    @MapsId("id_objeto")
    @JoinColumn(name = "id_objeto")
    private Objeto objeto;
}
