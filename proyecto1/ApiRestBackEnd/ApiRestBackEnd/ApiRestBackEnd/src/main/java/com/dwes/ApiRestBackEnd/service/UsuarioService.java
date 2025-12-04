package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.ficha.FichaDTO;
import com.dwes.ApiRestBackEnd.dto.ficha.FichaFindDTO;
import com.dwes.ApiRestBackEnd.dto.grupo.GrupoDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioFindDTO;
import com.dwes.ApiRestBackEnd.model.*;
import com.dwes.ApiRestBackEnd.repository.FichaRepository;
import com.dwes.ApiRestBackEnd.repository.GrupoRepository;
import com.dwes.ApiRestBackEnd.repository.JuegoRepository;
import com.dwes.ApiRestBackEnd.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final FichaServicee fichaServicee;
    private final GrupoRepository grupoRepository;
    private final JuegoRepository juegoRepository;
    private final FichaRepository fichaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, FichaServicee fichaServicee, GrupoRepository grupoRepository, JuegoRepository juegoRepository, FichaRepository fichaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.fichaServicee = fichaServicee;
        this.grupoRepository = grupoRepository;
        this.juegoRepository = juegoRepository;
        this.fichaRepository = fichaRepository;
    }

   public List<UsuarioFindDTO> findAll(){
        List<Usuario> usuarios=usuarioRepository.findUsuarioAll();
        List<UsuarioFindDTO> resultado=new ArrayList<>();
        for (Usuario usuario:usuarios){
            Long id=usuario.getId();
            String nombre=usuario.getNombre();
            String descripcion= usuario.getDescripcion();
            String contrasena= usuario.getContrasena();
            String imagen= usuario.getImagen_url();
            List<FichaDTO> fichaDTOS=new ArrayList<>();
            List<GrupoDTO> grupoDTOS= new ArrayList<>();
            List<JuegoDTO> juegoDTOS = new ArrayList<>();

            Set<FichaPersonaje> fichaPersonajes=usuario.getFichaPersonajes();

            for (FichaPersonaje fichaPersonaje: fichaPersonajes){
                String nombreFicha=fichaPersonaje.getNombre();
                fichaDTOS.add(new FichaDTO(nombreFicha));
            }
            List<Grupo>grupos=usuarioRepository.obtenerGrupodeUsuario(id);

            for (Grupo grupo:grupos){
                String nombreGru=grupo.getNombre();
                grupoDTOS.add(new GrupoDTO(nombreGru));
            }
            List<Juego> juegos=usuarioRepository.obtenerJuegodeUsuario(id);
            for(Juego juego:juegos) {
                String nombreJuego = juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }
            resultado.add(new UsuarioFindDTO(id,nombre,descripcion,contrasena,grupoDTOS,juegoDTOS,fichaDTOS,imagen));
        }
        return resultado;
    }

    public List<FichaFindDTO> findFichaByUsuario(String nombreUsuario){
        Usuario usuario = usuarioRepository.findByNombre(nombreUsuario);
        if (usuario == null) {
            return Collections.emptyList();
        }

        List<FichaPersonaje> fichaPersonajes = usuarioRepository.obtenerFichadeUsuario(usuario.getId());
        if (fichaPersonajes == null || fichaPersonajes.isEmpty()) {
            return Collections.emptyList();
        }

        List<FichaFindDTO> findDTOS = new ArrayList<>();
        for (FichaPersonaje fichaPersonaje : fichaPersonajes){
            String nombre = fichaPersonaje.getNombre();
            findDTOS.addAll(fichaServicee.findByNombre(nombre));
        }
        return findDTOS;
    }

    public List<GrupoDTO> findGrupos(String nombreUsuario){
        Usuario usuario=usuarioRepository.findByNombre(nombreUsuario);
        //List<UsuarioFindDTO> resultado=new ArrayList<>();
        List<Grupo>grupos=usuarioRepository.obtenerGrupodeUsuario(usuario.getId());
        return grupos.stream()
                .map(g->new GrupoDTO(g.getNombre()))
                .collect(Collectors.toList());
    }

    public List<UsuarioFindDTO> findAllbyNombre(String nombreUsuario){
        Usuario usuariNombr=usuarioRepository.findFirstByNombre(nombreUsuario);
        List<Usuario> usuarios=usuarioRepository.findAllById(usuariNombr.getId());
        List<UsuarioFindDTO> resultado=new ArrayList<>();
        for (Usuario usuario:usuarios){
            Long id=usuario.getId();
            String nombre=usuario.getNombre();
            String descripcion= usuario.getDescripcion();
            String contrasena= usuario.getContrasena();
            String imagen= usuario.getImagen_url();
            List<FichaDTO> fichaDTOS=new ArrayList<>();
            List<GrupoDTO> grupoDTOS= new ArrayList<>();
            List<JuegoDTO> juegoDTOS = new ArrayList<>();

            Set<FichaPersonaje> fichaPersonajes=usuario.getFichaPersonajes();

            for (FichaPersonaje fichaPersonaje: fichaPersonajes){
                String nombreFicha=fichaPersonaje.getNombre();
                fichaDTOS.add(new FichaDTO(nombreFicha));
            }
            List<Grupo>grupos=usuarioRepository.obtenerGrupodeUsuario(id);

            for (Grupo grupo:grupos){
                String nombreGru=grupo.getNombre();
                grupoDTOS.add(new GrupoDTO(nombreGru));
            }
            List<Juego> juegos=usuarioRepository.obtenerJuegodeUsuario(id);
            for(Juego juego:juegos) {
                String nombreJuego = juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }
            resultado.add(new UsuarioFindDTO(id,nombre,descripcion,contrasena,grupoDTOS,juegoDTOS,fichaDTOS,imagen));
        }
        return resultado;
    }

    public Usuario save(UsuarioDTO usuarioDTO){
        Usuario usuario=new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setDescripcion(usuarioDTO.getDescripcion());
        usuario.setContrasena(usuarioDTO.getContrasena());
        Set<Grupo> grupos = new HashSet<>(grupoRepository.findAllById(usuarioDTO.getGrupos()));
        usuario.setGrupos(grupos);
        Set<Juego> juegos = new HashSet<>(juegoRepository.findAllById(usuarioDTO.getJuegos()));
        usuario.setJuegos(juegos);
        usuario.setImagen_url(usuarioDTO.getImagen());

        return usuarioRepository.save(usuario);
    }

    public Usuario update(UsuarioDTO usuario,Long idUsuario){
        Usuario usuarioDB=usuarioRepository.findById(idUsuario).get();
        if(Objects.nonNull(usuario.getNombre())&&!"".equalsIgnoreCase(usuario.getNombre())){
            usuarioDB.setNombre(usuario.getNombre());
        }
        if(Objects.nonNull(usuario.getDescripcion())&&!"".equalsIgnoreCase(usuario.getDescripcion())){
            usuarioDB.setDescripcion(usuario.getDescripcion());
        }
        if(Objects.nonNull(usuario.getImagen())&&!"".equalsIgnoreCase(usuario.getImagen())){
            usuarioDB.setImagen_url(usuario.getImagen());
        }
        if (usuario.getGrupos() != null) {
            Set<Grupo> grupos = usuario.getGrupos().stream()
                    .map(id -> grupoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Grupo no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            usuarioDB.setGrupos(grupos);
        }
        if (usuario.getFichaPersonajes() != null) {
            Set<Juego> juegos = usuario.getFichaPersonajes().stream()
                    .map(id -> juegoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Ficha no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            usuarioDB.setJuegos(juegos);
        }
        if (Objects.nonNull(usuario.getContrasena())){
            usuarioDB.setContrasena(usuario.getContrasena());
        }
        if (usuario.getJuegos() != null) {
            Set<Juego> juegos = usuario.getJuegos().stream()
                    .map(id -> juegoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            usuarioDB.setJuegos(juegos);
        }
        return usuarioRepository.save(usuarioDB);
    }

    public void deleteById(Long idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.getGrupos().clear();
        usuario.getJuegos().clear();

        usuario.getFichaPersonajes().forEach(f -> f.setUsuario(null));
        usuario.getFichaPersonajes().clear();

        usuarioRepository.delete(usuario);
    }
}
