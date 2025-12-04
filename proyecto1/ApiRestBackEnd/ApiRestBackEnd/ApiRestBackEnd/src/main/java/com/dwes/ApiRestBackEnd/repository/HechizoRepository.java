package com.dwes.ApiRestBackEnd.repository;


import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HechizoRepository extends JpaRepository<Hechizo,Long> {
    @Query("SELECT h FROM Hechizo h")
    List<Hechizo> findHechizoAll();

    @Query("SELECT j from Juego j JOIN j.hechizos h WHERE h.id= :id")
    List<Juego> obtenerJuegodeHechizo(@Param("id") Long id);
}
