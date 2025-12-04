package com.dwes.ApiRestBackEnd.model.compositePK.juegoGrupo;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class JuegoGruposId implements Serializable {
    private Long id_juego;
    private Long id_grupo;

}

