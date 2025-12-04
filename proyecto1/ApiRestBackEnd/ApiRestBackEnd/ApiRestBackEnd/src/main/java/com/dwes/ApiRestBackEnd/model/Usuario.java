package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"juegos", "grupos", "fichaPersonajes"})
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String descripcion;
    private String contrasena;
    @JsonProperty("imagen")
    private String imagen_url;

    @ManyToMany
    @JoinTable(
            name = "grupos_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_grupo")
    )
    private Set<Grupo> grupos= new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "juego_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_juego"))
    private Set <Juego> juegos=new HashSet<>();

    @OneToMany(mappedBy = "usuario",  fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FichaPersonaje> fichaPersonajes;

}
