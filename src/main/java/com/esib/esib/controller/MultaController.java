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

import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.dto.MultaDTO;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.MultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/multas")
@RequiredArgsConstructor

public class MultaController {
    private final MultaService multaService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<MultaDTO>> findAll() {
        try {
            List<Multa> multas = multaService.findAll();
            List<MultaDTO> multaDTO = multas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(multaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/multa/{id}")
    public ResponseEntity<MultaDTO> findById(@PathVariable Long id) {
        try {
            Optional<Multa> multa = multaService.findById(id);
            return multa.map(d -> ResponseEntity.ok(convertToDTO(d)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MultaDTO>> findByEstado(@PathVariable String estado) {
        try {
            List<Multa> multas = multaService.findByEstado(estado);
            List<MultaDTO> multaDTO = multas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(multaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MultaDTO multaDTO) {
        try {
            Multa newMulta = multaService.create(convertToEntity(multaDTO));
            MultaDTO newMultaDTO = convertToDTO(newMulta);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newMultaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody MultaDTO multaDTO, @PathVariable Long id) {
        try {
            multaService.update(convertToEntity(multaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            multaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private MultaDTO convertToDTO(Multa multa) {

        MultaDTO multaDTO = new MultaDTO();
        multaDTO.setId(multa.getId());
        multaDTO.setValor(multa.getValorMulta());
        multaDTO.setEmprestimo(multa.getEmprestimo().getId());
        multaDTO.setEstado(multa.getEstado().getDescricao());

        return multaDTO;
    }

    private Multa convertToEntity(MultaDTO multaDTO) {

        Multa multa = new Multa();
        multa.setId(multaDTO.getId());
        multa.setValorMulta(multaDTO.getValor());
        multa.getEmprestimo().setId(multaDTO.getId());
        multa.setEstado(estadoService.findByDescricao(multaDTO.getEstado()));

        return multa;
    }
}
