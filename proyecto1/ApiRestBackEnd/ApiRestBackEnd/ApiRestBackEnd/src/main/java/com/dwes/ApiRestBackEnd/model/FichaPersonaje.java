package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "ficha_jugador")
public class FichaPersonaje extends Estadisticas{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String historia;
    private int nivel;
    private int experiencia=0;
    private String imagen_url;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juegos;

    @ManyToOne
    @JsonIgnoreProperties("fichas")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /*@ManyToMany
    private Set<Objeto> objetos;*/

    @ManyToMany
    @JoinTable(
            name = "ficha_hechizos",
            joinColumns = @JoinColumn(name = "id_fichaPersonaje"),
            inverseJoinColumns = @JoinColumn(name = "id_hechizo")
    )
    private Set<Hechizo> hechizos;


}
