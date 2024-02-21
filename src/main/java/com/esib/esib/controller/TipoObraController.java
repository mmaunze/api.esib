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

import com.esib.esib.modelo.TipoObra;
import com.esib.esib.modelo.dto.TipoObraDTO;
import com.esib.esib.service.TipoObraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tiposobras")
@RequiredArgsConstructor

public class TipoObraController {
    private final TipoObraService tipoObraService;

    @GetMapping()
    public ResponseEntity<List<TipoObraDTO>> findAll() {
        try {
            List<TipoObra> tipoObra = tipoObraService.findAll();
            List<TipoObraDTO> tipoObraDTO = tipoObra.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tipoObraDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipo/{id}")
    public ResponseEntity<TipoObraDTO> findById(@PathVariable Long id) {
        try {
            Optional<TipoObra> tipoObra = tipoObraService.findById(id);
            return tipoObra.map(to -> ResponseEntity.ok(convertToDTO(to)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoObraDTO tipoObraDTO) {
        try {
            TipoObra newtipoObra = tipoObraService.create(convertToEntity(tipoObraDTO));
            TipoObraDTO newtipoObraDTO = convertToDTO(newtipoObra);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newtipoObraDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoObraDTO tipoObraDTO, @PathVariable Long id) {
        try {
            tipoObraService.update(convertToEntity(tipoObraDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tipoObraService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private TipoObraDTO convertToDTO(TipoObra tipoObra) {

        TipoObraDTO tipoObraDTO = new TipoObraDTO();
        tipoObraDTO.setId(tipoObra.getId());
        tipoObraDTO.setDescricao(tipoObra.getDescricao());

        return tipoObraDTO;
    }

    private TipoObra convertToEntity(TipoObraDTO tipoObraDTO) {

        TipoObra tipoObra = new TipoObra();
        tipoObra.setId(tipoObraDTO.getId());
        tipoObra.setDescricao(tipoObraDTO.getDescricao());

        return tipoObra;
    }
}
