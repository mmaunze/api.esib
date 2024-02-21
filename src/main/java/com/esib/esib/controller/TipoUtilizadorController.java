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

import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.dto.TipoUtilizadorDTO;
import com.esib.esib.service.TipoUtilizadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tiposutilizadores")
@RequiredArgsConstructor

public class TipoUtilizadorController {
    private final TipoUtilizadorService tipoUtilizadorService;

    @GetMapping()
    public ResponseEntity<List<TipoUtilizadorDTO>> findAll() {
        try {
            List<TipoUtilizador> tipoUtilizador = tipoUtilizadorService.findAll();
            List<TipoUtilizadorDTO> tipoUtilizadorDTO = tipoUtilizador.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tipoUtilizadorDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipo/{id}")
    public ResponseEntity<TipoUtilizadorDTO> findById(@PathVariable Long id) {
        try {
            Optional<TipoUtilizador> tipoUtilizador = tipoUtilizadorService.findById(id);
            return tipoUtilizador.map(to -> ResponseEntity.ok(convertToDTO(to)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoUtilizadorDTO tipoUtilizadorDTO) {
        try {
            TipoUtilizador newtipoUtilizador = tipoUtilizadorService.create(convertToEntity(tipoUtilizadorDTO));
            TipoUtilizadorDTO newtipoUtilizadorDTO = convertToDTO(newtipoUtilizador);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newtipoUtilizadorDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoUtilizadorDTO tipoUtilizadorDTO, @PathVariable Long id) {
        try {
            tipoUtilizadorService.update(convertToEntity(tipoUtilizadorDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tipoUtilizadorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private TipoUtilizadorDTO convertToDTO(TipoUtilizador tipoUtilizador) {

        TipoUtilizadorDTO tipoUtilizadorDTO = new TipoUtilizadorDTO();
        tipoUtilizadorDTO.setId(tipoUtilizador.getId());
        tipoUtilizadorDTO.setDescricao(tipoUtilizador.getDescricao());

        return tipoUtilizadorDTO;
    }

    private TipoUtilizador convertToEntity(TipoUtilizadorDTO tipoUtilizadorDTO) {

        TipoUtilizador tipoUtilizador = new TipoUtilizador();
        tipoUtilizador.setId(tipoUtilizadorDTO.getId());
        tipoUtilizador.setDescricao(tipoUtilizadorDTO.getDescricao());

        return tipoUtilizador;
    }
}
