package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JuegoRepository extends JpaRepository<Juego,Long> {
    @Query("SELECT j FROM Juego j WHERE j.id = :id")
    Juego findByIdJuego(@Param("id") Long id);

    Juego findByNombre(String nombreJuego);

   /* @Query("SELECT c from Criaturas c JOIN c.juego j WHERE j.id= :id")
    List<Criaturas> obtenerCriaturasdeJuegos(@Param("id") Long id);*/

   /* @Query("SELECT l from Lugar l JOIN l.juego j WHERE j.id= :id")
    List<Lugar> obtenerLugardeJuego(@Param("id") Long id);*/
}
