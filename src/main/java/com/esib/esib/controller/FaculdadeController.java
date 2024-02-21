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

import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.dto.FaculdadeDTO;
import com.esib.esib.service.FaculdadeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/faculdades")
@RequiredArgsConstructor

public class FaculdadeController {
    private final FaculdadeService faculdadeService;

    @GetMapping()
    public ResponseEntity<List<FaculdadeDTO>> findAll() {
        try {
            List<Faculdade> faculdade = faculdadeService.findAll();
            List<FaculdadeDTO> faculdadeDTO = faculdade.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(faculdadeDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/faculdade/{id}")
    public ResponseEntity<FaculdadeDTO> findById(@PathVariable Long id) {
        try {
            Optional<Faculdade> faculdade = faculdadeService.findById(id);
            return faculdade.map(f -> ResponseEntity.ok(convertToDTO(f)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody FaculdadeDTO faculdadeDTO) {
        try {
            Faculdade newFaculdade = faculdadeService.create(convertToEntity(faculdadeDTO));
            FaculdadeDTO newFaculdadeDTO = convertToDTO(newFaculdade);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newFaculdadeDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody FaculdadeDTO faculdadeDTO, @PathVariable Long id) {
        try {
            faculdadeService.update(convertToEntity(faculdadeDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            faculdadeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private FaculdadeDTO convertToDTO(Faculdade faculdade) {

        FaculdadeDTO faculdadeDTO = new FaculdadeDTO();
        faculdadeDTO.setId(faculdade.getId());
        faculdadeDTO.setDescricao(faculdade.getDescricao());
        faculdadeDTO.setSigla(faculdade.getSigla());


        return faculdadeDTO;
    }

    private Faculdade convertToEntity(FaculdadeDTO faculdadeDTO) {

        Faculdade faculdade = new Faculdade();
        faculdade.setId(faculdadeDTO.getId());
        faculdade.setDescricao(faculdadeDTO.getDescricao());
        faculdade.setSigla(faculdadeDTO.getSigla());

        return faculdade;
    }
}
