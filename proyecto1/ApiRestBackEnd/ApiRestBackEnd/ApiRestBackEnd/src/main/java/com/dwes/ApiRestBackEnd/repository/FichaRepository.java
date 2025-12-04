package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.FichaPersonaje;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<FichaPersonaje,Long> {
    @Query("SELECT f FROM FichaPersonaje f")
    List<FichaPersonaje> findFichaPersonajeAll();

    @Query("SELECT u FROM Usuario u JOIN u.fichaPersonajes f WHERE f.id= :id")
    List<Usuario> obtenerUsuariodeFicha(@Param("id") Long id);

    @Query("SELECT j FROM Juego j JOIN j.fichaPersonajes f WHERE f.id= :id")
    List<Juego> obtenerJuegodeFicha(@Param("id") Long id);

    FichaPersonaje findByNombre(String nombreFicha);
}
