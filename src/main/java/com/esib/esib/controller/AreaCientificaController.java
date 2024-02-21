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

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.dto.AreaCientificaDTO;
import com.esib.esib.service.AreaCientificaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/areascientificas")
@RequiredArgsConstructor

public class AreaCientificaController {
    private final AreaCientificaService areaCientificaService;

    @GetMapping()
    public ResponseEntity<List<AreaCientificaDTO>> findAll() {
        try {
            List<AreaCientifica> areaCientifica = areaCientificaService.findAll();
            List<AreaCientificaDTO> areaCientificaDTO = areaCientifica.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(areaCientificaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<AreaCientificaDTO> findById(@PathVariable Long id) {
        try {
            Optional<AreaCientifica> areaCientifica = areaCientificaService.findById(id);
            return areaCientifica.map(a -> ResponseEntity.ok(convertToDTO(a)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody AreaCientificaDTO areaCientificaDTO) {
        try {
            AreaCientifica newAreaCientifica = areaCientificaService.create(convertToEntity(areaCientificaDTO));
            AreaCientificaDTO newAreaCientificaDTO = convertToDTO(newAreaCientifica);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newAreaCientificaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody AreaCientificaDTO areaCientificaDTO, @PathVariable Long id) {
        try {
            areaCientificaService.update(id, convertToEntity(areaCientificaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            areaCientificaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private AreaCientificaDTO convertToDTO(AreaCientifica areaCientifica) {

        AreaCientificaDTO areaCientificaDTO = new AreaCientificaDTO();
        areaCientificaDTO.setId(areaCientifica.getId());
        areaCientificaDTO.setDescricao(areaCientifica.getDescricao());

        return areaCientificaDTO;
    }

    private AreaCientifica convertToEntity(AreaCientificaDTO areaCientificaDTO) {

        AreaCientifica areaCientifica = new AreaCientifica();
        areaCientifica.setId(areaCientificaDTO.getId());
        areaCientifica.setDescricao(areaCientificaDTO.getDescricao());

        return areaCientifica;
    }
}
