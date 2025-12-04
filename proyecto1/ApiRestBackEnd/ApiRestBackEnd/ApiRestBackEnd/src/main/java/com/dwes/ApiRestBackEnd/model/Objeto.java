package com.dwes.ApiRestBackEnd.model;

import com.dwes.ApiRestBackEnd.model.compositePK.criaturaObjetos.CriaturasObjetos;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "objeto")
public class Objeto extends Estadisticas{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;

    @OneToOne
    @JoinColumn(name = "id_estadistica")
    private Estadisticas estadisticas;

    @ManyToMany
    private Set<FichaPersonaje> fichaPersonajes;
/*
    @ManyToMany(mappedBy = "objetos")
    private Set<Criaturas> criaturas;*/
@OneToMany(mappedBy = "objeto",cascade = CascadeType.ALL,orphanRemoval = true)
private Set<CriaturasObjetos> criaturasObjetos;

}
