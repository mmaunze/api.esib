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

import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.dto.CursoDTO;
import com.esib.esib.service.CursoService;
import com.esib.esib.service.FaculdadeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor

public class CursoController {
    private final CursoService cursoService;
    private final FaculdadeService faculdadeService;

    @GetMapping()
    public ResponseEntity<List<CursoDTO>> findAll() {
        try {
            List<Curso> curso = cursoService.findAll();
            List<CursoDTO> cursoDTO = curso.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(cursoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Curso> curso = cursoService.findById(id);
            return curso.map(c -> ResponseEntity.ok(convertToDTO(c)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody CursoDTO cursoDTO) {
        try {
            Curso newCurso = cursoService.create(convertToEntity(cursoDTO));
            CursoDTO newCursoDTO = convertToDTO(newCurso);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newCursoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody CursoDTO cursoDTO, @PathVariable Long id) {
        try {
            cursoService.update(convertToEntity(cursoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            cursoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private CursoDTO convertToDTO(Curso curso) {

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setDescricao(curso.getDescricao());
        cursoDTO.setFaculdade(curso.getFaculdade().getDescricao());

        return cursoDTO;
    }

    private Curso convertToEntity(CursoDTO cursoDTO) {

        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setDescricao(cursoDTO.getDescricao());
        curso.setFaculdade(faculdadeService.findByDescricao(cursoDTO.getFaculdade()));

        return curso;
    }
}
