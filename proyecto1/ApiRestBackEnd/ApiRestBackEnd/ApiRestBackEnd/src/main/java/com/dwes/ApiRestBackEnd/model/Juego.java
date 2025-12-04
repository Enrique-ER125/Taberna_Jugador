package com.dwes.ApiRestBackEnd.model;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "juego")
@EqualsAndHashCode(exclude = {"criaturas","usuarios","lugars"})
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String reglas;

    @JsonBackReference
    @ManyToMany(mappedBy = "juegos")
  /*  @JoinTable(
            name = "juego_grupos",
            joinColumns = @JoinColumn(name = "id_juego"),
            inverseJoinColumns = @JoinColumn(name = "id_grupo")
    )*/
    private Set<Grupo> grupos= new HashSet<>();


    @ManyToMany(mappedBy = "juegos")

    private Set<Usuario> usuarios= new HashSet<>();

   /* @OneToOne(mappedBy = "juegos")
    private Estadisticas estadisticas;*/

   /* @OneToMany(mappedBy = "juegos")
    private Set<Region> region;*/

    @OneToMany(mappedBy = "juegos", fetch = FetchType.EAGER)
    private Set<Lugar> lugars;

    @OneToMany(mappedBy = "juego")
    private Set<Hechizo> hechizos;

    @OneToMany(mappedBy = "juegos")
    private Set<FichaPersonaje> fichaPersonajes;

    @OneToMany(mappedBy = "juego")
    @JsonIgnore
    private Set<Criaturas> criaturas;

}
