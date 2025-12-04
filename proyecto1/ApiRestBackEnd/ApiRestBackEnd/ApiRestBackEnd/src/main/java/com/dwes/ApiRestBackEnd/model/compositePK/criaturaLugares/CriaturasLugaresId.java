package com.dwes.ApiRestBackEnd.model.compositePK.criaturaLugares;

import jakarta.persistence.Column;

import java.io.Serializable;

public class CriaturasLugaresId implements Serializable {
    @Column(name = "id_criaturas")
    private Long id_criaturas;

    @Column(name = "id_lugar")
    private Long id_lugar;
}
