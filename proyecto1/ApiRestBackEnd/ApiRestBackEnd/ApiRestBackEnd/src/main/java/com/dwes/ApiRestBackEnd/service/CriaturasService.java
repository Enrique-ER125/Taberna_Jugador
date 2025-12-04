package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaHechizoDTO;
import com.dwes.ApiRestBackEnd.dto.hechizos.HechizoDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Hechizo;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import com.dwes.ApiRestBackEnd.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CriaturasService {
    private final CriaturasRepository criaturasRepository;
    private final JuegoRepository juegoRepository;
    private final HechizoRepository hechizoRepository;
    private final LugarRepository lugarRepository;
    private final CriaturaHechizoRepository criaturaHechizoRepository;
    public CriaturasService(CriaturasRepository criaturasRepository, JuegoRepository juegoRepository, HechizoRepository hechizoRepository, LugarRepository lugarRepository, CriaturaHechizoRepository criaturaHechizoRepository) {
        this.criaturasRepository = criaturasRepository;
        this.juegoRepository = juegoRepository;
        this.hechizoRepository = hechizoRepository;
        this.lugarRepository = lugarRepository;
        this.criaturaHechizoRepository = criaturaHechizoRepository;
    }

    public List<CriaturaFindDTO> findAllById(Long idCriatura) {
        List<Criaturas> criaturas = criaturasRepository.findAllById(idCriatura);
        List<CriaturaFindDTO> resultado = new ArrayList<>();
        for (Criaturas criatura : criaturas){
            List<JuegoDTO> juegoDTOS=new ArrayList<>();
            List<HechizoDTO> hechizoDTOS=new ArrayList<>();
            List<LugarDTO> lugarDTOS=new ArrayList<>();
            Long id=criatura.getId();
            String nombre= criatura.getNombre();
            String descripcion= criatura.getDescripcion();
            int ataque_fisico= criatura.getAtaque_fisico();
            int salud= criatura.getSalud();
            int ataque_magico= criatura.getAtaque_magico();
            int mana= criatura.getMana();
            String imagen=criatura.getImagen_url();
            int nivel= criatura.getNivel();
            int rango= criatura.getRango();
            String historia=criatura.getHistoria();
            List<Hechizo> hechizos=criaturasRepository.obtenerHechizosdeCriaturas(id);
            for (Hechizo hechizo:hechizos){
                String nombreHechizo=hechizo.getNombre();
                String descripcionHechizo=hechizo.getDescripcion();
                String tipo=hechizo.getTipo();
                String elemento=hechizo.getElemento();
                int ataque_fisicoHechizo= hechizo.getAtaque_fisico();
                int saludHechizo=hechizo.getSalud();
                int ataque_magicoHechizo=hechizo.getAtaque_magico();
                int manaHechizo=hechizo.getMana();
                hechizoDTOS.add(new HechizoDTO(nombreHechizo,descripcionHechizo,tipo,elemento,ataque_fisicoHechizo,saludHechizo,ataque_magicoHechizo,manaHechizo));
            }
            List<Juego>juegos=criaturasRepository.obtenerJuegodeCriaturas(id);
            for (Juego juego:juegos){
                String nombreJuego= juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }
            List<Lugar>lugars=criaturasRepository.obtenerLugardeCriaturas(id);

            for (Lugar lugar:lugars){
                Long idLugar=lugar.getId();
                String nombreLugar= lugar.getNombre();
                String historiaLugar=lugar.getHistoria();
                String descripcionLugar=lugar.getDescripcion();
                lugarDTOS.add(new LugarDTO(idLugar,nombreLugar,historiaLugar,descripcionLugar));
            }

            resultado.add(new CriaturaFindDTO(id,nombre,descripcion,juegoDTOS,hechizoDTOS,historia,nivel,rango,ataque_fisico,salud,ataque_magico,mana,imagen,lugarDTOS));
        }
        return resultado;
    }

    public List<CriaturaFindDTO> findAll() {
        List<Criaturas> criaturas = criaturasRepository.findCriaturasAll();
        List<CriaturaFindDTO> resultado = new ArrayList<>();
        List<JuegoDTO> juegoDTOS=new ArrayList<>();
        List<HechizoDTO> hechizoDTOS=new ArrayList<>();
        List<LugarDTO> lugarDTOS=new ArrayList<>();
        for (Criaturas criatura : criaturas){
            Long id=criatura.getId();
            String nombre= criatura.getNombre();
            String descripcion= criatura.getDescripcion();
             int ataque_fisico= criatura.getAtaque_fisico();
             int salud= criatura.getSalud();
             int ataque_magico= criatura.getAtaque_magico();
             int mana= criatura.getMana();
             String imagen=criatura.getImagen_url();
            int nivel= criatura.getNivel();
            int rango= criatura.getRango();
            String historia=criatura.getHistoria();

            List<Hechizo> hechizos=criaturasRepository.obtenerHechizosdeCriaturas(id);

            for (Hechizo hechizo:hechizos){
                String nombreHechizo=hechizo.getNombre();
                String descripcionHechizo=hechizo.getDescripcion();
                String tipo=hechizo.getTipo();
                String elemento=hechizo.getElemento();
                int ataque_fisicoHechizo= hechizo.getAtaque_fisico();
                int saludHechizo=hechizo.getSalud();
                int ataque_magicoHechizo=hechizo.getAtaque_magico();
                int manaHechizo=hechizo.getMana();
                hechizoDTOS.add(new HechizoDTO(nombreHechizo,descripcionHechizo,tipo,elemento,ataque_fisicoHechizo,saludHechizo,ataque_magicoHechizo,manaHechizo));
            }

            List<Juego>juegos=criaturasRepository.obtenerJuegodeCriaturas(id);

            for (Juego juego:juegos){
                String nombreJuego= juego.getNombre();
                juegoDTOS.add(new JuegoDTO(nombreJuego));
            }

            List<Lugar>lugars=criaturasRepository.obtenerLugardeCriaturas(id);

            for (Lugar lugar:lugars){
                Long idLugar=lugar.getId();
                String nombreLugar= lugar.getNombre();
                String historiaLugar=lugar.getHistoria();
                String descripcionLugar=lugar.getDescripcion();
                lugarDTOS.add(new LugarDTO(idLugar,nombreLugar,historiaLugar,descripcionLugar));
            }

            resultado.add(new CriaturaFindDTO(id,nombre,descripcion,juegoDTOS,hechizoDTOS,historia,nivel,rango,ataque_fisico,salud,ataque_magico,mana,imagen,lugarDTOS));
        }
        return resultado;
    }

public List<Hechizo> findHechizos(Long idCriatura){
    List<Hechizo> hechizos=criaturaHechizoRepository.obtenerHechizosdeCriaturas(idCriatura);
    return hechizos;
}

    public Criaturas save(CriaturaDTO dto) {
        Criaturas criatura = new Criaturas();
        criatura.setNombre(dto.getNombre());
        criatura.setAtaque_fisico(dto.getAtaque_fisico());
        criatura.setAtaque_magico(dto.getAtaque_magico());
        criatura.setSalud(dto.getSalud());
        criatura.setMana(dto.getMana());
        criatura.setDescripcion(dto.getDescripcion());
        criatura.setNivel(dto.getNivel());
        criatura.setRango(dto.getRango());
        criatura.setHistoria(dto.getHistoria());
        if (dto.getLugars() != null) {
            Set<Lugar> lugares = dto.getLugars().stream()
                    .map(id -> lugarRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            criatura.setLugars(lugares);
        }

        criatura.setImagen_url(dto.getImagen());
        Juego juego = juegoRepository.findById(dto.getIdJuego()).orElseThrow();
        criatura.setJuego(juego);
        if (dto.getHechizos() != null) {
            Set<Hechizo> hechizos = dto.getHechizos().stream()
                    .map(id -> hechizoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Hechizo no encontrado con ID: " + id)))
                    .collect(Collectors.toSet());
            criatura.setHechizos(hechizos);
        }
        return criaturasRepository.save(criatura);
    }

    public Criaturas update(Criaturas criaturas,Long idCriaturas) {
        Criaturas criaturasDB= criaturasRepository.findById(idCriaturas).get();
        if (Objects.nonNull(criaturas.getNombre())&&!"".equalsIgnoreCase(criaturas.getNombre())){
            criaturasDB.setNombre(criaturas.getNombre());
        }
        if (Objects.nonNull(criaturas.getDescripcion())&&!"".equalsIgnoreCase(criaturas.getDescripcion())){
            criaturasDB.setDescripcion(criaturas.getDescripcion());
        }
        if (Objects.nonNull(criaturas.getHistoria())&&!"".equalsIgnoreCase(criaturas.getHistoria())){
            criaturasDB.setHistoria(criaturas.getHistoria());
        }
        if (Objects.nonNull(criaturas.getRango())){
            criaturasDB.setRango(criaturas.getRango());
        }
        if(Objects.nonNull(criaturas.getImagen_url())&&!"".equalsIgnoreCase(criaturas.getImagen_url())){
            criaturasDB.setImagen_url(criaturas.getImagen_url());
        }
        if (Objects.nonNull(criaturas.getJuego())){
            criaturasDB.setJuego(criaturas.getJuego());
        }
        if (Objects.nonNull(criaturas.getHechizos())){
            criaturasDB.setHechizos(criaturas.getHechizos());
        }
        if (Objects.nonNull(criaturas.getLugars())){
            criaturasDB.setLugars(criaturas.getLugars());
        }
       /* if (Objects.nonNull(criaturas.getCriaturasObjetos())){
             criaturasDB.setCriaturasObjetos(criaturas.getCriaturasObjetos() );
        }
        if (Objects.nonNull(criaturas.getCriaturasLugares())){
            criaturasDB.setCriaturasLugares(criaturas.getCriaturasLugares());
        }*/
        return criaturasRepository.save(criaturasDB);
    }

    @Transactional
    public void deleteById(Long idCriatura) {
        Criaturas criaturas = criaturasRepository.findById(idCriatura)
                .orElseThrow(() -> new EntityNotFoundException("Criatura no encontrado"));
        criaturas.getHechizos().clear();
        criaturas.getLugars().clear();
        Long idJuego=criaturas.getJuego().getId();
        Juego juego =juegoRepository.findById(idJuego).orElseThrow(() -> new EntityNotFoundException("Juego no encontrado"));
        juego.getCriaturas().remove(criaturas);

        criaturasRepository.delete(criaturas);
    }
}
