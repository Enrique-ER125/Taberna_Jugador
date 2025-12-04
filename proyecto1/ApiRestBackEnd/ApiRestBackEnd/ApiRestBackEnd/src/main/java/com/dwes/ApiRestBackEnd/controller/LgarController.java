package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarFindDTO;
import com.dwes.ApiRestBackEnd.model.Lugar;
import com.dwes.ApiRestBackEnd.service.LugarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/lugar")
public class LgarController {
    private final LugarService lugarService;

    public LgarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Lugar> save(@RequestBody LugarDTO dto){
        return ResponseEntity.ok(lugarService.save(dto));
    }

    @GetMapping("/lugares")
    public List<LugarFindDTO> findAll() {
        return lugarService.findAll();
    }

    @GetMapping("/buscarId")
    public List<LugarFindDTO> findAllById(@RequestParam Long idLugar){
        return lugarService.findAllById(idLugar);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteById( @PathVariable Long id) throws Exception {
        lugarService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
