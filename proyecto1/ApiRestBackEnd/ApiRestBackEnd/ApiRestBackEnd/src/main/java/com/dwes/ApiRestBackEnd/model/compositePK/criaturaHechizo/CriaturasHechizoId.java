package com.dwes.ApiRestBackEnd.model.compositePK.criaturaHechizo;

import jakarta.persistence.Column;

import java.io.Serializable;

public class CriaturasHechizoId implements Serializable {
    @Column(name = "id_criaturas")
    private Long id_criaturas;

    @Column(name = "id_hechizo")
    private Long id_hechizo;

    public Long getIdHechizo() {
        return id_hechizo;
    }
}
