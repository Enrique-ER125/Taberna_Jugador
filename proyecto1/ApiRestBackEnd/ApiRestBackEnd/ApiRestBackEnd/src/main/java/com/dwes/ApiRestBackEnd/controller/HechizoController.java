package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoFindDTO;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.service.HechizoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("hechizo")
//@RequiredArgsConstructor
public class HechizoController {
    private final HechizoService hechizoService;

    public HechizoController(HechizoService hechizoService) {
        this.hechizoService = hechizoService;
    }

    @GetMapping("/listar")
    public List<HechizoFindDTO> listar(){
        return hechizoService.findAll();
    }

    @PostMapping("/crear")
    public Hechizo crear(@Valid @RequestBody Hechizo hechizo)  {
        return hechizoService.save(hechizo);
    }

    @PutMapping("/actualizar")
    public Hechizo update(@Valid @RequestBody Hechizo hechizo, Long idHechizo){
        return hechizoService.update(hechizo, idHechizo);
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Void> deleteById(Long idHechizo) throws Exception{
        hechizoService.deleteById(idHechizo);
        return ResponseEntity.noContent().build();
    }

}
