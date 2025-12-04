package com.dwes.ApiRestBackEnd.model;

import com.dwes.ApiRestBackEnd.model.compositePK.criaturaHechizo.CriaturasHechizo;
import com.dwes.ApiRestBackEnd.model.compositePK.criaturaLugares.CriaturasLugares;
import com.dwes.ApiRestBackEnd.model.compositePK.criaturaObjetos.CriaturasObjetos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "criaturas")
@EqualsAndHashCode(exclude = {"juego","lugars","hechizos"})
public class Criaturas extends Estadisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private int nivel;
    private int rango;
    private String historia;
    private String imagen_url;

    @ManyToOne
    @JoinColumn(name = "id_juego",referencedColumnName = "id")
    @JsonIgnore
    private Juego juego;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "criaturas_hechizos",
            joinColumns = @JoinColumn(name = "id_criaturas"),
            inverseJoinColumns = @JoinColumn(name = "id_hechizo")
    )
    private Set<Hechizo> hechizos=new HashSet<>();

   /* @OneToMany(mappedBy = "criaturas", cascade = CascadeType.ALL,orphanRemoval = true)


    @ManyToMany
    @JoinTable(
            name = "criaturas_objetos",
            joinColumns = @JoinColumn(name = "id_criaturas"),
            inverseJoinColumns = @JoinColumn(name = "id_objetos")
    )
    private Set<Objeto> objetos;*/

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "criaturas_lugares",
            joinColumns = @JoinColumn(name = "id_criaturas"),
            inverseJoinColumns = @JoinColumn(name = "id_lugar")
    )
    private Set<Lugar> lugars=new HashSet<>();


}
