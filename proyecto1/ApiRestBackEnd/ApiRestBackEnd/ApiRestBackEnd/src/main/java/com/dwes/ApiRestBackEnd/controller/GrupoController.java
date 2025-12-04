package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.grupo.GrupoDTO;
import com.dwes.ApiRestBackEnd.dto.grupo.GrupoFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.model.Grupo;
import com.dwes.ApiRestBackEnd.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/grupo")
public class GrupoController {
    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping("/listar")
    public List<GrupoFindDTO> listar(){
        return grupoService.findAll();
    }

    @GetMapping("/listarByNombre/{nombreGrupo}")
    public List<GrupoFindDTO> listarByNombre(@PathVariable String nombreGrupo){
        return grupoService.findAllByNombre(nombreGrupo);
    }

    @PostMapping("crear")
    public Grupo crear(@Valid @RequestBody GrupoDTO grupo){
        return grupoService.save(grupo);
    }

    @GetMapping("/buscarJuegos")
    public List<JuegoDTO> obtenerJuegosdeGrupo(@RequestParam String nombreGrupo){
        List<JuegoDTO> juego=grupoService.obtenerJuegosdeGrupos(nombreGrupo);
        return juego;
    }

    @PutMapping("/actualizar/{idGrupo}")
    public ResponseEntity<Grupo> update(@Valid @RequestBody GrupoDTO grupo, @PathVariable Long idGrupo){
        Grupo actualizar= grupoService.update(grupo, idGrupo);
        return ResponseEntity.ok(actualizar);
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Void> deleteById(Long idGrupo){
        grupoService.deleteById(idGrupo);
        return ResponseEntity.noContent().build();
    }
}
