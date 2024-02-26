package com.esib.esib.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.dto.ReservaDTO;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.ReservaService;
import com.esib.esib.service.UtilizadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor

public class ReservaController {

    private final ReservaService reservaService;
    private final UtilizadorService utilizadorService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<ReservaDTO>> findAll() {
        try {
            List<Reserva> reservas = reservaService.findAll();
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
        try {
            Optional<Reserva> reserva = reservaService.findById(id);
            return reserva.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ReservaDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Reserva> reservas = reservaService.findByTitulo(titulo);
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<ReservaDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Reserva> reservas = reservaService.findByIdioma(idioma);
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<ReservaDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Reserva> reservas = reservaService.findByAreaCientifica(areacientifica);
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/utilizador/{utilizador}")
    public ResponseEntity<List<ReservaDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            List<Reserva> reservas = reservaService.findByUtilizador(utilizador);
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obra/{obra}")
    public ResponseEntity<List<ReservaDTO>> findByObra(@PathVariable Long obra) {
        try {
            List<Reserva> reservas = reservaService.findByObra(obra);
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody ReservaDTO reservaDTO) {
        try {
            Reserva newReserva = reservaService.create(convertToEntity(reservaDTO));
            ReservaDTO newReservaDTO = convertToDTO(newReserva);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newReservaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaDTO reservaDTO, @PathVariable Long id) {
        try {
            reservaService.update(convertToEntity(reservaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            reservaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ReservaDTO convertToDTO(Reserva reserva) {

        ReservaDTO reservaDTO = new ReservaDTO();

        reservaDTO.setId(reserva.getId());

        reservaDTO.setUtilizador(reserva.getUtilizador().getId());
        reservaDTO.setUtlizadorNome(reserva.getUtilizador().getNome());

        reservaDTO.setTituloObra(reserva.getObra().getTitulo());
        reservaDTO.setDataReserva(reserva.getDataReserva());

        reservaDTO.setEstado(reserva.getEstado().getDescricao());

        return reservaDTO;
    }

    private Reserva convertToEntity(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setId(reservaDTO.getId());

        reserva.setId(reservaDTO.getId());
        if (utilizadorService.findById(reservaDTO.getUtilizador()).isPresent())
            reserva.setUtilizador(utilizadorService.findById(reservaDTO.getUtilizador()).get());
        reserva.setDataReserva(reservaDTO.getDataReserva());
        reserva.setEstado(estadoService.findByDescricao(reservaDTO.getEstado()));

        return reserva;
    }
}
