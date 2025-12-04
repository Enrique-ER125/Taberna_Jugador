package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.criaturas.CriaturaFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoFindDTO;
import com.dwes.ApiRestBackEnd.dto.juego.JuegoUpdateDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarDTO;
import com.dwes.ApiRestBackEnd.dto.lugar.LugarMapper;
import com.dwes.ApiRestBackEnd.model.Criaturas;
import com.dwes.ApiRestBackEnd.model.Juego;
import com.dwes.ApiRestBackEnd.model.Lugar;
import com.dwes.ApiRestBackEnd.repository.CriaturasRepository;
import com.dwes.ApiRestBackEnd.repository.JuegoRepository;
import com.dwes.ApiRestBackEnd.repository.LugarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JuegoService {
    private  final JuegoRepository juegoRepository;
    private final CriaturasService criaturasService;
    private final CriaturasRepository criaturasRepository;
    private final LugarRepository lugarRepository;
    private final LugarMapper lugarMapper;

    public JuegoService(JuegoRepository juegoRepository, CriaturasService criaturasService, CriaturasRepository criaturasRepository, LugarRepository lugarRepository, LugarMapper lugarMapper) {
        this.juegoRepository = juegoRepository;
        this.criaturasService = criaturasService;
        this.criaturasRepository = criaturasRepository;
        this.lugarRepository = lugarRepository;
        this.lugarMapper = lugarMapper;
    }

    public List<JuegoFindDTO> finAllNombre(String nombreJuego){
        Juego juego = juegoRepository.findByNombre(nombreJuego);
        Long id = juego.getId();
        return findAllById(id);
    }


    public List<JuegoFindDTO> findAllById(Long idJuego){
        Juego juegos =juegoRepository.findByIdJuego(idJuego);
        List<JuegoFindDTO> resultado= new ArrayList<>();
        List<CriaturaFindDTO> criaturaDTOS=new ArrayList<>();
        List<LugarDTO> lugarDTOS=new ArrayList<>();
       String nombre= juegos.getNombre();
       String descripcion= juegos.getDescripcion();
       String reglas= juegos.getReglas();
       Set<Criaturas> criaturas = juegos.getCriaturas();
       for(Criaturas criatura:criaturas){
           Long id=criatura.getId();
            criaturaDTOS.addAll(criaturasService.findAllById(id));
       }
       Set<Lugar> lugars=juegos.getLugars();
       for (Lugar lugar:lugars){
           Long id= lugar.getId();
           String nombreLugar=lugar.getNombre();
           String historia=lugar.getHistoria();
           String descripcionLugar=lugar.getDescripcion();
           lugarDTOS.add(new LugarDTO(id,nombreLugar,historia,descripcionLugar));
       }
       resultado.add(new JuegoFindDTO(nombre,descripcion,reglas,criaturaDTOS,lugarDTOS));
        return resultado;
    }

    public Criaturas convertirDTOaEntidad(CriaturaFindDTO dto) {
        Criaturas criatura = new Criaturas();
        criatura.setId(dto.getId());
        criatura.setNombre(dto.getNombre());
        criatura.setDescripcion(dto.getDescripcion());

        return criatura;
    }


    public Juego update (JuegoUpdateDTO juego, Long idJuego){
        Juego juegoDB=juegoRepository.findById(idJuego).get();
        if (Objects.nonNull(juego.getNombre())&&!"".equalsIgnoreCase(juego.getNombre())){
            juegoDB.setNombre(juego.getNombre());
        }
        if (Objects.nonNull(juego.getDescripcion())&&!"".equalsIgnoreCase(juego.getDescripcion())){
            juegoDB.setDescripcion(juego.getDescripcion());
        }
        if (Objects.nonNull(juego.getReglas())&&!"".equalsIgnoreCase(juego.getReglas())){
            juegoDB.setReglas(juego.getReglas());
        }
        if(Objects.nonNull(juego.getCriatura())){

            juegoDB.getCriaturas().clear();
            for (CriaturaFindDTO c : juego.getCriatura()) {
                // Mejor: cargar por ID
                Criaturas cDB = criaturasRepository.findById(c.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Criatura no encontrada: " + c.getId()));
                juegoDB.getCriaturas().add(cDB);
            }
        }
        if(Objects.nonNull(juego.getLugar())){
            for (Lugar l : new HashSet<>(juegoDB.getLugars())) {
                l.setJuegos(null);
            }
            juegoDB.getLugars().clear();
            for (LugarDTO lDto : juego.getLugar()) {
                Lugar lugar;
                if (lDto.getId() != null) {
                    lugar = lugarRepository.findById(lDto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Lugar no encontrado: " + lDto.getId()));

                    if (lDto.getNombre() != null) lugar.setNombre(lDto.getNombre());
                    if (lDto.getDescripcion() != null) lugar.setDescripcion(lDto.getDescripcion());
                    if (lDto.getHistoria() != null) lugar.setHistoria(lDto.getHistoria());
                } else {
                    lugar = new Lugar();
                    lugar.setNombre(lDto.getNombre());
                    lugar.setDescripcion(lDto.getDescripcion());
                    lugar.setHistoria(lDto.getHistoria());
                }
                lugar.setJuegos(juegoDB);
                juegoDB.getLugars().add(lugar);
            }
        }
        return juegoRepository.save(juegoDB);
    }




    public Juego save(JuegoDTO dto){
        Juego juego=new Juego();
        juego.setNombre(dto.getNombre());
        juego.setDescripcion(dto.getDescripcion());
        juego.setReglas(dto.getReglas());

        return juegoRepository.save(juego);
    }

}
