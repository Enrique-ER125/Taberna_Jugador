package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.combate.CombateDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Lugar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CombateService {
    public int tirarDado() {
        Random rd = new Random();
        return 1 + rd.nextInt(12);
    }

    public double ataqueFisico(double fuerza, double arma, double saludE, int dado) {
        double total = (fuerza + arma) * ((double) dado / 12.0);
        return saludE - total;
    }

    public double ataqueFisico(double fuerza, double saludT, int dado) {
        double total = fuerza * ((double) dado / 12.0);
        return saludT - total;
    }


    public CombateDTO explorarLugar(Lugar lugar) {
        Set<Criaturas> criaturasSet = lugar.getCriaturas();
        List<CriaturaFindDTO> criaturas = criaturasSet.stream()
                .map(c -> new CriaturaFindDTO(
                        c.getId(),
                        c.getNombre(),
                        c.getDescripcion(),
                        c.getAtaque_fisico(),
                        c.getSalud(),
                        c.getAtaque_magico(),
                        c.getMana(),
                        c.getNivel(),
                        c.getRango(),
                        c.getHistoria()
                ))
                .collect(Collectors.toList());
        CriaturaFindDTO boss = criaturas.get(criaturas.size() - 1); // último es el boss

        return new CombateDTO(lugar.getNombre(), criaturas, boss);
    }
}
