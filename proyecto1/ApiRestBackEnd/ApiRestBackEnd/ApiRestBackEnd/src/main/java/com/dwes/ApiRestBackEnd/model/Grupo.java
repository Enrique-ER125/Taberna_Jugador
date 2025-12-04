package com.dwes.ApiRestBackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "juego_grupos",
            joinColumns = @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_juego")
    )
    private Set<Juego> juegos=new HashSet<>();

    @ManyToMany(mappedBy = "grupos")
    private Set<Usuario> usuarios=new HashSet<>();
}
