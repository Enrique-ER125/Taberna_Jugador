package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "estaditicas")
public class Estadisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ataque_fisico;
    private int salud;
    private int ataque_magico;
    private int mana;
    private int defensa_fisica;
    private int defensa_magica;

//    @OneToOne
//    @JoinColumn(name = "id_juego")
//    private Juego juegos;
//
//    @OneToOne(mappedBy = "estadisticas")
//    private Objeto objetos;
//
//   @JsonBackReference
//   @OneToOne(mappedBy = "estadisticas")
//    private Hechizo hechizo;
//
//    @OneToOne(mappedBy = "estadisticas")
//    private FichaPersonaje fichaPersonaje;

//    @JsonBackReference
//    @OneToOne(mappedBy = "estadisticas")
//    private Criaturas criaturas;
}
