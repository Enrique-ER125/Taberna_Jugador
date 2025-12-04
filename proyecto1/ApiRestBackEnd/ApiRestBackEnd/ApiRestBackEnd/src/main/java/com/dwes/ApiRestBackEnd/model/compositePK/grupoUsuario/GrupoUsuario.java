package com.dwes.ApiRestBackEnd.model.compositePK.grupoUsuario;

import com.dwes.ApiRestBackEnd.model.Grupo;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupos_usuario")
public class GrupoUsuario {
    @EmbeddedId
    private GrupoUsuarioId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("id_usuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("id_grupo")
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
}
