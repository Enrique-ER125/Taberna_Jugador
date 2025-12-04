package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriaturasRepository extends JpaRepository<Criaturas,Long> {
    @Query("SELECT h.id FROM Criaturas c JOIN c.hechizos h WHERE c.id = :id")
    List<Long> findHechizosById(@Param("id") Long id);

    @Query("SELECT h from Hechizo h JOIN h.criaturas c WHERE c.id= :id")
    List<Hechizo> obtenerHechizosdeCriaturas(@Param("id") Long id);

    @Query("SELECT j from Juego j JOIN j.criaturas c WHERE c.id= :id")
    List<Juego> obtenerJuegodeCriaturas(@Param("id") Long id);

    @Query("SELECT l from Lugar l JOIN l.criaturas c WHERE c.id= :id")
    List<Lugar> obtenerLugardeCriaturas(@Param("id") Long id);

    @Query("SELECT c FROM Criaturas c")
    List<Criaturas> findCriaturasAll();

    List<Criaturas> findAllById(Long idCriatura);
    // @Query("DELETE FROM Criaturas_hechizos c WHERE c.id= ?")


}
