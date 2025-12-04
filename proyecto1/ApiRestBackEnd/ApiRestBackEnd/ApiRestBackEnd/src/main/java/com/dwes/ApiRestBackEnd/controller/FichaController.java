package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.ficha.FichaDTO;
import com.dwes.ApiRestBackEnd.dto.ficha.FichaFindDTO;
import com.dwes.ApiRestBackEnd.model.FichaPersonaje;
import com.dwes.ApiRestBackEnd.service.FichaServicee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("ficha")
public class FichaController {
    private final FichaServicee fichaServicee;

    public FichaController(FichaServicee fichaServicee) {
        this.fichaServicee = fichaServicee;
    }

    @GetMapping()
    public List<FichaFindDTO> listar (){
        return  fichaServicee.findAll();
    }

    @PostMapping("/crear")
    public FichaPersonaje crear(@Valid @RequestBody FichaDTO fichaDTO){
        return fichaServicee.save(fichaDTO);
    }

    @PutMapping("/update/{idFicha}")
    public void update(@Valid @RequestBody FichaDTO fichaPersonaje, @PathVariable Long idFicha){
        fichaServicee.update(fichaPersonaje, idFicha);
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Void> deleteById(Long idFicha){
        fichaServicee.deleteById(idFicha);
        return ResponseEntity.noContent().build();
    }
}
