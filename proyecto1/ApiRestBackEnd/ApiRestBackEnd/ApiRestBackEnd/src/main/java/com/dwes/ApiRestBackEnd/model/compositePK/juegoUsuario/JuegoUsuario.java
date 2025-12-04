package com.dwes.ApiRestBackEnd.model.compositePK.juegoUsuario;

import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "juego_usuario")
public class JuegoUsuario {
    @EmbeddedId
    private JuegoUsuarioId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_juego")
    @JoinColumn(name = "id_juego")
    private Juego juego;

    @ManyToOne
    @MapsId("id_usuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
