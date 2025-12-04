package com.dwes.ApiRestBackEnd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "region")
public class Region  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String descripcion;
    private String historia;


  /*  @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juegos;

    @OneToMany(mappedBy = "region")
    private Set<Lugar> lugars;*/
}
