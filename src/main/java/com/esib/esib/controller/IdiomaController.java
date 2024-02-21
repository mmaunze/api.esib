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

import com.esib.esib.modelo.Idioma;
import com.esib.esib.modelo.dto.IdiomaDTO;
import com.esib.esib.service.IdiomaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/idiomas")
@RequiredArgsConstructor

public class IdiomaController {
    private final IdiomaService idiomaService;

    @GetMapping()
    public ResponseEntity<List<IdiomaDTO>> findAll() {
        try {
            List<Idioma> idioma = idiomaService.findAll();
            List<IdiomaDTO> idiomaDTO = idioma.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(idiomaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{id}")
    public ResponseEntity<IdiomaDTO> findById(@PathVariable Long id) {
        try {
            Optional<Idioma> idioma = idiomaService.findById(id);
            return idioma.map(i -> ResponseEntity.ok(convertToDTO(i)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody IdiomaDTO idiomaDTO) {
        try {
            Idioma newIdioma = idiomaService.create(convertToEntity(idiomaDTO));
            IdiomaDTO newIdiomaDTO = convertToDTO(newIdioma);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newIdiomaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody IdiomaDTO idiomaDTO, @PathVariable Long id) {
        try {
            idiomaService.update(convertToEntity(idiomaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            idiomaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private IdiomaDTO convertToDTO(Idioma idioma) {

        IdiomaDTO idiomaDTO = new IdiomaDTO();
        idiomaDTO.setId(idioma.getId());
        idiomaDTO.setDescricao(idioma.getDescricao());

        return idiomaDTO;
    }

    private Idioma convertToEntity(IdiomaDTO idiomaDTO) {

        Idioma idioma = new Idioma();
        idioma.setId(idiomaDTO.getId());
        idioma.setDescricao(idiomaDTO.getDescricao());

        return idioma;
    }
}
