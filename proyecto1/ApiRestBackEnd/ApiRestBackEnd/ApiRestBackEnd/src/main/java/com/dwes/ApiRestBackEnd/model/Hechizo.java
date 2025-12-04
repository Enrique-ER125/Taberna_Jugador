package com.dwes.ApiRestBackEnd.model;

import com.dwes.ApiRestBackEnd.model.compositePK.criaturaHechizo.CriaturasHechizo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "hechizo")
public class Hechizo extends Estadisticas{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String elemento;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;

    @ManyToMany(mappedBy = "hechizos")
    private Set<FichaPersonaje> fichaPersonajes;

    @ManyToMany//(mappedBy = "hechizos")
    @JoinTable(
            name = "criaturas_hechizos",
            joinColumns = @JoinColumn(name = "id_hechizo"),
            inverseJoinColumns = @JoinColumn(name = "id_criaturas")
    )
    private Set<Criaturas> criaturas;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_estadistica",referencedColumnName = "id")
//    private Estadisticas estadisticas;
     /*
    @OneToMany( mappedBy = "hechizo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CriaturasHechizo> criaturasHechizos;*/
}
