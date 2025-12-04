package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.grupo.GrupoDTO;
import com.dwes.ApiRestBackEnd.dto.grupo.GrupoFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioDTO;
import com.dwes.ApiRestBackEnd.model.Grupo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.dwes.ApiRestBackEnd.repository.GrupoRepository;
import com.dwes.ApiRestBackEnd.repository.JuegoRepository;
import com.dwes.ApiRestBackEnd.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GrupoService {
    private  final GrupoRepository grupoRepository;
    private final JuegoRepository juegoRepository;
    private final UsuarioRepository usuarioRepository;

    public GrupoService(GrupoRepository grupoRepository, JuegoRepository juegoRepository, UsuarioRepository usuarioRepository) {
        this.grupoRepository = grupoRepository;
        this.juegoRepository = juegoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<GrupoFindDTO> findAllByNombre(String nombreGrupo){
        Grupo gruponombre= grupoRepository.findByNombre(nombreGrupo);
        List<Grupo> grupos= grupoRepository.findAllById(gruponombre.getId());
        List<GrupoFindDTO> resultado = new ArrayList<>();
        List<UsuarioDTO> usuarioDTOS=new ArrayList<>();
        for (Grupo grupo: grupos){
            Long id=grupo.getId();
            String nombre=grupo.getNombre();
            List<Usuario> usuario= grupoRepository.obtenerUsuariodeGrupo(id);
            for (Usuario usuarios: usuario){
                String nombreUsuarios=usuarios.getNombre();
                String descripcion= usuarios.getDescripcion();
                String imagen=usuarios.getImagen_url();
                usuarioDTOS.add(new UsuarioDTO(nombreUsuarios,descripcion,imagen));
            }
            List<Juego> juego=grupoRepository.obtenerJuegodeGrupo(id);
            for(Juego juegos:juego){
                String nombreJuego=juegos.getNombre();
                String descripcion=juegos.getDescripcion();
                String reglas=juegos.getReglas();
                List<JuegoDTO> juegoDTOS = new ArrayList<>();
                juegoDTOS.add(new JuegoDTO(nombreJuego,descripcion,reglas));
                resultado.add(new GrupoFindDTO(id,nombre,juegoDTOS, usuarioDTOS));
            }

        }
        return resultado;
    }

    public List<GrupoFindDTO> findAll(){
        List<Grupo> grupos= grupoRepository.findGrupoAll();

        List<GrupoFindDTO> resultado = new ArrayList<>();
        List<UsuarioDTO> usuarioDTOS=new ArrayList<>();
        for (Grupo grupo: grupos){
            Long id=grupo.getId();
            String nombre=grupo.getNombre();
            List<Usuario> usuario= grupoRepository.obtenerUsuariodeGrupo(id);
            for (Usuario usuarios: usuario){
                String nombreUsuarios=usuarios.getNombre();
                String descripcion= usuarios.getDescripcion();
                String imagen=usuarios.getImagen_url();
                usuarioDTOS.add(new UsuarioDTO(nombreUsuarios,descripcion,imagen));
            }
            List<Juego> juego=grupoRepository.obtenerJuegodeGrupo(id);
            for(Juego juegos:juego){
                 String nombreJuego=juegos.getNombre();
                 String descripcion=juegos.getDescripcion();
                 String reglas=juegos.getReglas();
                 List<JuegoDTO> juegoDTOS = new ArrayList<>();
                 juegoDTOS.add(new JuegoDTO(nombreJuego,descripcion,reglas));
                resultado.add(new GrupoFindDTO(id,nombre,juegoDTOS, usuarioDTOS));
            }

        }
        return resultado;
    }

    public List<JuegoDTO> obtenerJuegosdeGrupos(String nombreGrupos){
        Grupo grupo=grupoRepository.findByNombre(nombreGrupos);
        List<Juego> juegos= grupoRepository.obtenerJuegodeGrupo(grupo.getId());
        return juegos.stream()
                .map(j -> new JuegoDTO(j.getNombre()))
                .collect(Collectors.toList());
    }

    public Grupo save(GrupoDTO dto){
        Grupo grupo = new Grupo();
        //Set<Juego> juegos = new HashSet<>(juegoRepository.findAllById(dto.getJuegos())
        //Set<Usuario> usuarios= new HashSet<>(usuarioRepository.findAllById());
        grupo.setNombre(dto.getNombre());

        Set<Juego> juegos = new HashSet<>(juegoRepository.findAllById(dto.getJuegos()));
        grupo.setJuegos(juegos);

        Set<Usuario> usuarios = new HashSet<>(usuarioRepository.findAllById(dto.getUsuarios()));
        grupo.setUsuarios(usuarios);
        return grupoRepository.save(grupo);
    }

    public Grupo update(GrupoDTO grupo,Long idGrupo){
        Grupo grupoDB=grupoRepository.findById(idGrupo).get();
        if (Objects.nonNull(grupo.getNombre())&&!"".equalsIgnoreCase(grupo.getNombre())){
            grupoDB.setNombre(grupo.getNombre());
        }
        if (Objects.nonNull(grupo.getJuegos())){
            Set<Juego> juegos = grupo.getJuegos().stream()
                    .map(id -> juegoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            grupoDB.setJuegos(juegos);
        }
        if (Objects.nonNull(grupo.getUsuarios())){
            Set<Usuario> usuarios = grupo.getUsuarios().stream()
                    .map(id -> usuarioRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            grupoDB.setUsuarios(usuarios);
        }
        return grupoRepository.save(grupoDB);
    }

    public void deleteById(Long idGrupo){
        grupoRepository.deleteById(idGrupo);
    }

}
