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

import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.dto.EstadoDTO;
import com.esib.esib.service.EstadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor

public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<EstadoDTO>> findAll() {
        try {
            List<Estado> estado = estadoService.findAll();
            List<EstadoDTO> estadoDTO = estado.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(estadoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Estado> estado = estadoService.findById(id);
            return estado.map(e -> ResponseEntity.ok(convertToDTO(e)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EstadoDTO estadoDTO) {
        try {
            Estado newEstado = estadoService.create(convertToEntity(estadoDTO));
            EstadoDTO newEstadoDTO = convertToDTO(newEstado);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newEstadoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody EstadoDTO estadoDTO, @PathVariable Long id) {
        try {
            estadoService.update(convertToEntity(estadoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            estadoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private EstadoDTO convertToDTO(Estado estado) {

        EstadoDTO estadoDTO = new EstadoDTO();
        estadoDTO.setId(estado.getId());
        estadoDTO.setDescricao(estado.getDescricao());

        return estadoDTO;
    }

    private Estado convertToEntity(EstadoDTO estadoDTO) {

        Estado estado = new Estado();
        estado.setId(estadoDTO.getId());
        estado.setDescricao(estadoDTO.getDescricao());

        return estado;
    }
}
