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

import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.dto.DepartamentoDTO;
import com.esib.esib.service.DepartamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/departamentos")
@RequiredArgsConstructor

public class DepartamentoController {
    private final DepartamentoService departamentoService;

    @GetMapping()
    public ResponseEntity<List<DepartamentoDTO>> findAll() {
        try {
            List<Departamento> departamento = departamentoService.findAll();
            List<DepartamentoDTO> departamentoDTO = departamento.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Departamento> departamento = departamentoService.findById(id);
            return departamento.map(d -> ResponseEntity.ok(convertToDTO(d)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            Departamento newDepartamento = departamentoService.create(convertToEntity(departamentoDTO));
            DepartamentoDTO newDepartamentoDTO = convertToDTO(newDepartamento);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newDepartamentoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody DepartamentoDTO departamentoDTO, @PathVariable Long id) {
        try {
            departamentoService.update(convertToEntity(departamentoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            departamentoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private DepartamentoDTO convertToDTO(Departamento departamento) {

        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(departamento.getId());
        departamentoDTO.setDescricao(departamento.getDescricao());
        departamentoDTO.setSigla(departamento.getSigla());


        return departamentoDTO;
    }

    private Departamento convertToEntity(DepartamentoDTO departamentoDTO) {

        Departamento departamento = new Departamento();
        departamento.setId(departamentoDTO.getId());
        departamento.setDescricao(departamentoDTO.getDescricao());
        departamento.setSigla(departamentoDTO.getSigla());

        return departamento;
    }
}
