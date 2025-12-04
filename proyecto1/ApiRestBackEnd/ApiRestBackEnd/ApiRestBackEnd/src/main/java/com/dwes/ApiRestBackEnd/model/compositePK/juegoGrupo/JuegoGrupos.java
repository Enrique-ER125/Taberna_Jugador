package com.dwes.ApiRestBackEnd.model.compositePK.juegoGrupo;

import com.dwes.ApiRestBackEnd.model.Grupo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "juego_grupos")
public class JuegoGrupos {
    @EmbeddedId
    private JuegoGruposId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_juego")
    @JoinColumn(name = "id_juego")
    private Juego juego;

    @ManyToOne
    @MapsId("id_grupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
}
