package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.ficha.FichaDTO;
import com.dwes.ApiRestBackEnd.dto.ficha.FichaFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioDTO;
import com.dwes.ApiRestBackEnd.model.*;
import com.dwes.ApiRestBackEnd.repository.FichaRepository;
import com.dwes.ApiRestBackEnd.repository.HechizoRepository;
import com.dwes.ApiRestBackEnd.repository.JuegoRepository;
import com.dwes.ApiRestBackEnd.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FichaServicee {
    private final FichaRepository fichaRepository;
    private final JuegoRepository juegoRepository;
    private final UsuarioRepository usuarioRepository;
    private final HechizoRepository hechizoRepository;

    public FichaServicee(FichaRepository fichaRepository, JuegoRepository juegoRepository, UsuarioRepository usuarioRepository, HechizoRepository hechizoRepository) {
        this.fichaRepository = fichaRepository;
        this.juegoRepository = juegoRepository;
        this.usuarioRepository = usuarioRepository;
        this.hechizoRepository = hechizoRepository;
    }

    public List<FichaFindDTO> findByNombre(String nombreFicha){
        FichaPersonaje fichaPersonaje=fichaRepository.findByNombre(nombreFicha);
       // List<FichaPersonaje> ficha=fichaRepository.findFichaPersonajeAll();
        List<FichaFindDTO> resultado=new ArrayList<>();
        List<JuegoDTO> juegoDTOS=new ArrayList<>();
        List<UsuarioDTO> usuarioDTOS=new ArrayList<>();

            Long id=fichaPersonaje.getId();
            String nombre= fichaPersonaje.getNombre();
            String descripcion= fichaPersonaje.getDescripcion();
            String historia= fichaPersonaje.getHistoria();
            String imagen= fichaPersonaje.getImagen_url();
            int ataque_fisico= fichaPersonaje.getAtaque_fisico();
            int salud=fichaPersonaje.getSalud();
            int ataque_magico=fichaPersonaje.getAtaque_magico();
            int mana=fichaPersonaje.getMana();
            int nivel=fichaPersonaje.getNivel();
            int experiencia=fichaPersonaje.getExperiencia();
            List<Juego> juegos=fichaRepository.obtenerJuegodeFicha(id);
            for (Juego juego:juegos){
                String nombreJuego= juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }
            List<Usuario> usuario=fichaRepository.obtenerUsuariodeFicha(id);
            for (Usuario usuarios: usuario){
                String nombreUsuarios=usuarios.getNombre();
                String imagenUsuario=usuarios.getImagen_url();
                usuarioDTOS.add(new UsuarioDTO(nombreUsuarios,imagenUsuario));
            }
            resultado.add(new FichaFindDTO(id,nombre,descripcion,historia,ataque_fisico,salud,ataque_magico,mana,nivel,experiencia,juegoDTOS,usuarioDTOS,imagen));
        return resultado;
    }



    public List<FichaFindDTO> findAll(){
       List<FichaPersonaje> ficha=fichaRepository.findFichaPersonajeAll();
       List<FichaFindDTO> resultado=new ArrayList<>();
       List<JuegoDTO> juegoDTOS=new ArrayList<>();
       List<UsuarioDTO> usuarioDTOS=new ArrayList<>();
       for(FichaPersonaje fichaPersonaje:ficha){
            Long id=fichaPersonaje.getId();
           String nombre= fichaPersonaje.getNombre();
           String descripcion= fichaPersonaje.getDescripcion();
           String historia= fichaPersonaje.getHistoria();
           String imagen= fichaPersonaje.getImagen_url();
           int ataque_fisico= fichaPersonaje.getAtaque_fisico();
           int salud=fichaPersonaje.getSalud();
           int ataque_magico=fichaPersonaje.getAtaque_magico();
           int mana=fichaPersonaje.getMana();
           int nivel=fichaPersonaje.getNivel();
           int experiencia=fichaPersonaje.getExperiencia();
           List<Juego> juegos=fichaRepository.obtenerJuegodeFicha(id);
           for (Juego juego:juegos){
               String nombreJuego= juego.getNombre();
               juegoDTOS.add(new JuegoDTO(nombreJuego));
           }
           List<Usuario> usuario=fichaRepository.obtenerUsuariodeFicha(id);
           for (Usuario usuarios: usuario){
               String nombreUsuarios=usuarios.getNombre();
               String imagenUsuario=usuarios.getImagen_url();
               usuarioDTOS.add(new UsuarioDTO(nombreUsuarios,imagenUsuario));
           }
           resultado.add(new FichaFindDTO(nombre,descripcion,historia,ataque_fisico,salud,ataque_magico,mana,nivel,experiencia,juegoDTOS,usuarioDTOS,imagen));
       }
       return resultado;
    }

    public FichaPersonaje save(FichaDTO fichaDTO){
         FichaPersonaje fichaPersonaje=new FichaPersonaje();
         fichaPersonaje.setNombre(fichaDTO.getNombre());
         fichaPersonaje.setDescripcion(fichaDTO.getDescripcion());
         fichaPersonaje.setHistoria(fichaDTO.getHistoria());
         fichaPersonaje.setAtaque_fisico(fichaDTO.getAtaque_fisico());
         fichaPersonaje.setSalud(fichaDTO.getSalud());
         fichaPersonaje.setAtaque_magico(fichaDTO.getAtaque_magico());
         fichaPersonaje.setMana(fichaDTO.getMana());
         fichaPersonaje.setImagen_url(fichaDTO.getImagen());
         fichaPersonaje.setNivel(fichaDTO.getNivel());

        Juego juego = juegoRepository.findById(fichaDTO.getJuegoId()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(fichaDTO.getUsuarioId()).orElseThrow();
        fichaPersonaje.setJuegos(juego);
         fichaPersonaje.setUsuario(usuario);

        return fichaRepository.save(fichaPersonaje);
    }

    public FichaPersonaje update(FichaDTO fichaPersonaje,Long idFichaje){
        FichaPersonaje fichaPersonajeDB=fichaRepository.findById(idFichaje).get();
        if (Objects.nonNull(fichaPersonaje.getDescripcion())&&!"".equalsIgnoreCase(fichaPersonajeDB.getDescripcion())){
            fichaPersonajeDB.setDescripcion(fichaPersonaje.getDescripcion());
        }
        if (Objects.nonNull(fichaPersonaje.getNombre())&&!"".equalsIgnoreCase(fichaPersonaje.getNombre())){
            fichaPersonajeDB.setNombre(fichaPersonaje.getNombre());
        }
        if (fichaPersonaje.getJuegoId() != null) {
            Juego juego = juegoRepository.findById(fichaPersonaje.getJuegoId())
                    .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado con ID: " + fichaPersonaje.getJuegoId()));
            fichaPersonajeDB.setJuegos(juego);
        }
        if (fichaPersonaje.getHechizos() != null) {
            Set<Hechizo> hechizos = fichaPersonaje.getHechizos().stream()
                    .map(id -> hechizoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            fichaPersonajeDB.setHechizos(hechizos);
        }
        if (fichaPersonaje.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(fichaPersonaje.getUsuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + fichaPersonaje.getUsuarioId()));
            fichaPersonajeDB.setUsuario(usuario);
        }
        if(Objects.nonNull(fichaPersonaje.getImagen())&&!"".equalsIgnoreCase(fichaPersonaje.getImagen())){
            fichaPersonajeDB.setImagen_url(fichaPersonaje.getImagen());
        }
        if(Objects.nonNull(fichaPersonaje.getAtaque_fisico())){
            fichaPersonajeDB.setAtaque_fisico(fichaPersonaje.getAtaque_fisico());
        }
        if(Objects.nonNull(fichaPersonaje.getNivel())){
            fichaPersonajeDB.setNivel(fichaPersonaje.getNivel());
        }
        if(Objects.nonNull(fichaPersonaje.getAtaque_magico())){
            fichaPersonajeDB.setAtaque_magico(fichaPersonaje.getAtaque_magico());
        }
        if(Objects.nonNull(fichaPersonaje.getSalud())){
            fichaPersonajeDB.setSalud(fichaPersonaje.getSalud());
        }
        if(Objects.nonNull(fichaPersonaje.getMana())){
            fichaPersonajeDB.setMana(fichaPersonaje.getMana());
        }
        if(Objects.nonNull(fichaPersonaje.getExperiencia())){
            fichaPersonajeDB.setExperiencia(fichaPersonaje.getExperiencia());
        }
        return fichaRepository.save(fichaPersonajeDB);
    }

    public void deleteById(Long idFicha){
        fichaRepository.deleteById(idFicha);
    }
}
