package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.FichaPersonaje;
import com.dwes.ApiRestBackEnd.model.Grupo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   @Query("SELECT u FROM Usuario u")
    List<Usuario> findUsuarioAll();

    @Query("SELECT g FROM Grupo g JOIN g.usuarios u WHERE u.id= :id")
    List<Grupo> obtenerGrupodeUsuario(@Param("id") Long id);

    @Query("SELECT f from FichaPersonaje f JOIN f.usuario u WHERE u.id= :id")
    List<FichaPersonaje> obtenerFichadeUsuario(@Param("id") Long id);

    @Query("SELECT j FROM Juego j JOIN j.usuarios u WHERE u.id= :id")
    List<Juego> obtenerJuegodeUsuario(@Param("id") Long id);

    @Query("SELECT u FROM Usuario u JOIN u.grupos g JOIN u.juegos j WHERE g.nombre = :nombreGrupo AND j.nombre = :nombreJuego ")
    List<Usuario> findByGrupoAndJuegoAndLugar(@Param("nombreGrupo") String nombreGrupo, @Param("nombreJuego") String nombreJuego);

 List<Usuario> findAllById(Long idUsuario);

    Usuario findByNombre(String nombreUsuario);

    Usuario findFirstByNombre(String nombre);
}
