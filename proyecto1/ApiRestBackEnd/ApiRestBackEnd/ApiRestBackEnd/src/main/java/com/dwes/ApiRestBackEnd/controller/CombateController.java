package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.combate.CombateDTO;
import com.dwes.ApiRestBackEnd.model.Lugar;
import com.dwes.ApiRestBackEnd.repository.LugarRepository;
import com.dwes.ApiRestBackEnd.service.CombateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combate")
public class CombateController {
    private CombateService combateService;

    @Autowired
    private LugarRepository lugarRepository;

    @GetMapping("/iniciar")
    public CombateDTO iniciarCombate(@RequestParam Long idLugar) {
        Lugar lugar = lugarRepository.findById(idLugar)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));

        return combateService.explorarLugar(lugar);
    }
}
