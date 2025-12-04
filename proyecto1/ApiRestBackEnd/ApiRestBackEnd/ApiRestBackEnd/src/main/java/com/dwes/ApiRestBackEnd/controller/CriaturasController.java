package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaHechizoDTO;
import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.service.CriaturasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("criaturas")
//@RequiredArgsConstructor
public class CriaturasController {
    private final CriaturasService criaturaService;

    public CriaturasController(CriaturasService criaturaService) {
        this.criaturaService = criaturaService;
    }

    @GetMapping("/listar")
    public List<CriaturaFindDTO> listar(){
        return criaturaService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<Criaturas> crear(@RequestBody CriaturaDTO dto) {
        return  ResponseEntity.ok(criaturaService.save(dto));
    }
    @PutMapping("/actualizar")
    public Criaturas update(@RequestBody Criaturas criaturas,@PathVariable ("id") Long idCriaturas)  {
        return criaturaService.update(criaturas,idCriaturas);
    }

    @GetMapping("/hechizo")
    public List<Hechizo> findHechizo(Long idCriaturas){
        return criaturaService.findHechizos(idCriaturas);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
         criaturaService.deleteById(id);
         return ResponseEntity.noContent().build();
    }

}
