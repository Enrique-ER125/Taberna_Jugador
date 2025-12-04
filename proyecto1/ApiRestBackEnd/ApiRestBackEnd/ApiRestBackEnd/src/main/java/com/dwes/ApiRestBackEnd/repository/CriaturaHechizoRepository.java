package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.compositePK.criaturaHechizo.CriaturasHechizo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface   CriaturaHechizoRepository extends JpaRepository<CriaturasHechizo,Long> {

    @Modifying
    @Query("DELETE FROM CriaturasHechizo ch WHERE ch.criaturas.id = :id")
    void deleteByCriaturaId(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM CriaturasHechizo ch WHERE ch.hechizo.id = :id")
    void deleteByHechizoId(@Param("id") Long id);

    @Modifying
    @Query("SELECT h from Hechizo h where h.id = (SELECT ch.hechizo.id FROM CriaturasHechizo ch WHERE ch.criaturas.id in :id)")
    List<Hechizo> obtenerHechizosdeCriaturas(@Param("id") Long id);

}
