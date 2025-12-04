package com.dwes.ApiRestBackEnd.model;

import com.dwes.ApiRestBackEnd.model.compositePK.criaturaLugares.CriaturasLugares;
import com.dwes.ApiRestBackEnd.model.compositePK.criaturaObjetos.CriaturasObjetos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lugar")
@EqualsAndHashCode(exclude = {"criaturas","juegos"})
public class Lugar  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String historia;

   /* @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;*/

    @ManyToOne
    @JoinColumn(name = "id_juego",referencedColumnName = "id")
    private Juego juegos;

    @ManyToMany
    @JoinTable(
            name = "criaturas_lugares",
            joinColumns = @JoinColumn(name = "id_lugar"),
            inverseJoinColumns = @JoinColumn(name = "id_criaturas")
    )
    private Set<Criaturas> criaturas = new HashSet<>();


}
