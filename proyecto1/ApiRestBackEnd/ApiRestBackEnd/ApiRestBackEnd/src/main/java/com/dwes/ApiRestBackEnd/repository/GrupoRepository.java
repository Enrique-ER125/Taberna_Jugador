package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.model.Grupo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  GrupoRepository extends JpaRepository<Grupo,Long> {

    @Query("SELECT g FROM Grupo g")
    List<Grupo> findGrupoAll();


   // @Query("SELECT u FROM Usuario u WHERE u.id=(SELECT gu.usuario.id FROM GrupoUsuario gu WHERE gu.grupo.id in :id)")
    @Query("SELECT u FROM Usuario u JOIN u.grupos g WHERE g.id= :id")
    List<Usuario> obtenerUsuariodeGrupo(@Param("id") Long id);


    @Query("SELECT j FROM Juego j JOIN j.grupos g WHERE g.id= :id")
    List<Juego> obtenerJuegodeGrupo(@Param("id") Long id);

    Grupo findByNombre(String nombreGrupos);

    List<Grupo> findAllById(Long id);
}
