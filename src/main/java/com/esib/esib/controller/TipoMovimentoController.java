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

import com.esib.esib.modelo.TipoMovimento;
import com.esib.esib.modelo.dto.TipoMovimentoDTO;
import com.esib.esib.service.TipoMovimentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tiposmovimentos")
@RequiredArgsConstructor

public class TipoMovimentoController {
    private final TipoMovimentoService tipoMovimentoService;

    @GetMapping()
    public ResponseEntity<List<TipoMovimentoDTO>> findAll() {
        try {
            List<TipoMovimento> tipoMovimento = tipoMovimentoService.findAll();
            List<TipoMovimentoDTO> tipoMovimentoDTO = tipoMovimento.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tipoMovimentoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipo/{id}")
    public ResponseEntity<TipoMovimentoDTO> findById(@PathVariable Long id) {
        try {
            Optional<TipoMovimento> tipoMovimento = tipoMovimentoService.findById(id);
            return tipoMovimento.map(a -> ResponseEntity.ok(convertToDTO(a)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoMovimentoDTO tipoMovimentoDTO) {
        try {
            TipoMovimento newtipoMovimento = tipoMovimentoService.create(convertToEntity(tipoMovimentoDTO));
            TipoMovimentoDTO newtipoMovimentoDTO = convertToDTO(newtipoMovimento);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newtipoMovimentoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoMovimentoDTO tipoMovimentoDTO, @PathVariable Long id) {
        try {
            tipoMovimentoService.update(convertToEntity(tipoMovimentoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tipoMovimentoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private TipoMovimentoDTO convertToDTO(TipoMovimento tipoMovimento) {

        TipoMovimentoDTO tipoMovimentoDTO = new TipoMovimentoDTO();
        tipoMovimentoDTO.setId(tipoMovimento.getId());
        tipoMovimentoDTO.setDescricao(tipoMovimento.getDescricao());

        return tipoMovimentoDTO;
    }

    private TipoMovimento convertToEntity(TipoMovimentoDTO tipoMovimentoDTO) {

        TipoMovimento tipoMovimento = new TipoMovimento();
        tipoMovimento.setId(tipoMovimentoDTO.getId());
        tipoMovimento.setDescricao(tipoMovimentoDTO.getDescricao());

        return tipoMovimento;
    }
}
