package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.ficha.FichaFindDTO;
import com.dwes.ApiRestBackEnd.dto.grupo.GrupoDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.LoginDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioDTO;
import com.dwes.ApiRestBackEnd.dto.usuario.UsuarioFindDTO;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.dwes.ApiRestBackEnd.repository.JuegoRepository;
import com.dwes.ApiRestBackEnd.repository.UsuarioRepository;
import com.dwes.ApiRestBackEnd.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final JuegoRepository juegoRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, JuegoRepository juegoRepository, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.juegoRepository = juegoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
   public ResponseEntity<UsuarioFindDTO> login (@RequestBody LoginDTO loginDTO){
        List<UsuarioFindDTO> usuarios =usuarioService.findAll();
        for (UsuarioFindDTO usuario :usuarios){
            if (usuario.getNombre().equals(loginDTO.getNombre())&&
            usuario.getContrasena().equals(loginDTO.getContrasena())){
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/buscarBynombre")
    public List<UsuarioFindDTO> findAllbyNombre(String nombreUsuario){
        return usuarioService.findAllbyNombre(nombreUsuario);
    }

    @GetMapping("/buscarGrupos")
    public List<GrupoDTO> findGrupos(@RequestParam String nombreUsuario){
        List<GrupoDTO> findDTOS=usuarioService.findGrupos(nombreUsuario);
        return findDTOS;
    }

    @GetMapping("/buscarFichaByUsuario")
    public ResponseEntity<List<FichaFindDTO>> findFichaByUsuario(@RequestParam String nombreUsuario) {
        List<FichaFindDTO> fichas = usuarioService.findFichaByUsuario(nombreUsuario);
        return ResponseEntity.ok(fichas);
    }

    @GetMapping("/listar")
    public List<UsuarioFindDTO> listar(){
        return usuarioService.findAll();
    }

    @GetMapping("/jugadoresEnLugar")
    public ResponseEntity<List<UsuarioDTO>> listarJugadoresEnLugar(
            @RequestParam String nombreGrupo,
            @RequestParam String nombreJuego) {

        List<Usuario> usuarios = usuarioRepository.findByGrupoAndJuegoAndLugar(
                nombreGrupo, nombreJuego
        );

        List<UsuarioDTO> jugadores = usuarios.stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNombre()))
                .toList();

        return ResponseEntity.ok(jugadores);
    }

    @PostMapping("/agregarJugador")
    public ResponseEntity<Void> agregarJugador(@RequestParam String nombreJuego,
                                               @RequestParam String nombreUsuario) {
        Juego juego = juegoRepository.findByNombre(nombreJuego);
        Usuario usuario = usuarioRepository.findByNombre(nombreUsuario);

        if (juego == null || usuario == null) {
            return ResponseEntity.notFound().build();
        }

        juego.getUsuarios().add(usuario);
        juegoRepository.save(juego);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/crear")
    public Usuario crear(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.save(usuarioDTO);
    }

    @PutMapping("/update/{idUsuario}")
    public ResponseEntity<Usuario> update(@Valid @RequestBody UsuarioDTO usuario,@PathVariable Long idUsuario){
        Usuario actualizado = usuarioService.update(usuario, idUsuario);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/borrar/{idUsuario}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idUsuario){
        usuarioService.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }



}
