package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoUpdateDTO;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.service.JuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/juegos")
public class JuegoController {
    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @PostMapping("/crear")
    public Juego crear(JuegoDTO dto){
        return juegoService.save(dto);
    }

    @GetMapping("/buscarId")
    public List<JuegoFindDTO> findAllById(@RequestParam Long idJuego){
        List<JuegoFindDTO> juegoFindDTOS=juegoService.findAllById(idJuego);
        return juegoFindDTOS;
    }

    @GetMapping("/buscarNombre")
    public List<JuegoFindDTO> findAllNombre(String nombreJuego){
        List<JuegoFindDTO> juegoFindDTOS=juegoService.finAllNombre(nombreJuego);
        return juegoFindDTOS;
    }

    @PutMapping("/actualizar/{id}")
    public Void update(@RequestBody JuegoUpdateDTO juego, @PathVariable("id") Long idJuego){
         juegoService.update(juego, idJuego);
        return null;
    }
}
