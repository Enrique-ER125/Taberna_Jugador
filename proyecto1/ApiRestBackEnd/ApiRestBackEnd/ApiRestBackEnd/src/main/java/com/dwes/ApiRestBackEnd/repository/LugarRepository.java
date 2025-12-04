package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository<Lugar,Long> {
    @Query("SELECT l FROM Lugar l")
    List<Lugar> findLugarAll();

    Lugar findAllById(Long idLugar);

    @Query("SELECT c from Criaturas c JOIN c.lugars l WHERE l.id= :id")
    List<Criaturas> obtenerCriaturasdeLugar(@Param("id") Long id);

    @Query("SELECT j from Juego j JOIN j.lugars l WHERE l.id= :id")
    List<Juego> obtenerJuegodeLugar(@Param("id") Long id);
}
