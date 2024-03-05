package com.esib.esib.controller;

import com.esib.esib.model.Reserva;
import com.esib.esib.model.dto.ReservaDTO;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.ReservaService;
import com.esib.esib.service.UtilizadorService;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

/**
 *
 * @author Meldo Maunze
 */
@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor

public class ReservaController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(ReservaController.class.getName());

    /**
     *
     */
    private final ReservaService reservaService;

    /**
     *
     */
    private final UtilizadorService utilizadorService;

    /**
     *
     */
    private final EstadoService estadoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<ReservaDTO>> findAll() {
        try {
            var reservas = reservaService.findAll();
            var reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(reservasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/reserva/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
        try {
            var reserva = reservaService.findById(id);
            return reserva.map(u -> ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param titulo
     * @return
     */
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ReservaDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var reservas = reservaService.findByTitulo(titulo);
            var reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(reservasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param idioma
     * @return
     */
    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<ReservaDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var reservas = reservaService.findByIdioma(idioma);
            var reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(reservasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param areacientifica
     * @return
     */
    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<ReservaDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var reservas = reservaService.findByAreaCientifica(areacientifica);
            var reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(reservasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param utilizador
     * @return
     */
    @GetMapping("/utilizador/{utilizador}")
    public ResponseEntity<List<ReservaDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            var reservas = reservaService.findByUtilizador(utilizador);
            var reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(reservasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param obra
     * @return
     */
    @GetMapping("/obra/{obra}")
    public ResponseEntity<List<ReservaDTO>> findByObra(@PathVariable Long obra) {
        try {
            var reservas = reservaService.findByObra(obra);
            var reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(reservasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param reservaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody ReservaDTO reservaDTO) {
        try {
            var newReserva = reservaService.create(convertToEntity(reservaDTO));
            var newReservaDTO = convertToDTO(newReserva);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newReservaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param reservaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaDTO reservaDTO, @PathVariable Long id) {
        try {
            reservaService.update(convertToEntity(reservaDTO));
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            reservaService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param reserva
     * @return
     */
    private ReservaDTO convertToDTO(Reserva reserva) {

        var reservaDTO = new ReservaDTO();

        reservaDTO.setId(reserva.getId());

        reservaDTO.setUtilizador(reserva.getUtilizador().getId());
        reservaDTO.setUtlizadorNome(reserva.getUtilizador().getNome());

        reservaDTO.setTituloObra(reserva.getObra().getTitulo());
        reservaDTO.setDataReserva(reserva.getDataReserva());

        reservaDTO.setEstado(reserva.getEstado().getDescricao());

        return reservaDTO;
    }

    /**
     *
     * @param reservaDTO
     * @return
     */
    private Reserva convertToEntity(ReservaDTO reservaDTO) {
        var reserva = new Reserva();
        reserva.setId(reservaDTO.getId());

        reserva.setId(reservaDTO.getId());
        if (utilizadorService.findById(reservaDTO.getUtilizador()).isPresent()) {
            reserva.setUtilizador(utilizadorService.findById(reservaDTO.getUtilizador()).get());
        }
        reserva.setDataReserva(reservaDTO.getDataReserva());
        reserva.setEstado(estadoService.findByDescricao(reservaDTO.getEstado()));

        return reserva;
    }

}
