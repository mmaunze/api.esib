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

import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.dto.EstudanteDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.CursoService;
import com.esib.esib.service.DepartamentoService;
import com.esib.esib.service.EstudanteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilizadores/estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    private final EstudanteService estudanteService;
    private final DepartamentoService departamentoService;
    private final CursoService cursoService;
    private final AreaCientificaService areaCientificaService;

    @GetMapping()
    public ResponseEntity<List<EstudanteDTO>> findAll() {
        try {
            List<Estudante> estudantes = estudanteService.findAll();
            List<EstudanteDTO> estudantesDTO = estudantes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(estudantesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estudante/{id}")
    public ResponseEntity<EstudanteDTO> findById(@PathVariable Long id) {
        try {
            Optional<Estudante> estudante = estudanteService.findById(id);
            return estudante.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<EstudanteDTO> findByUsername(@PathVariable String username) {
        try {
            Optional<Estudante> estudante = estudanteService.findByUsername(username);
            return estudante.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacto/{contacto}")
    public ResponseEntity<EstudanteDTO> findByContacto(@PathVariable String contacto) {
        try {
            Optional<Estudante> estudante = estudanteService.findByContacto(contacto);
            return estudante.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EstudanteDTO> findByEmail(@PathVariable String email) {
        try {
            Optional<Estudante> estudante = estudanteService.findByEmail(email);
            return estudante.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EstudanteDTO estudanteDTO) {
        try {
            Estudante newEstudante = estudanteService.create(convertToEntity(estudanteDTO));
            EstudanteDTO newEstudanteDTO = convertToDTO(newEstudante);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newEstudanteDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody EstudanteDTO estudanteDTO, @PathVariable Long id) {
        try {
            estudanteService.update(convertToEntity(estudanteDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            estudanteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Métodos auxiliares para conversão entre Entidade e DTO
     */

    private EstudanteDTO convertToDTO(Estudante estudante) {

        EstudanteDTO estudanteDTO = new EstudanteDTO();

        estudanteDTO.setId(estudante.getUtilizador().getId());
        estudanteDTO.setNome(estudante.getUtilizador().getNome());
        estudanteDTO.setEmail(estudante.getUtilizador().getEmail());
        estudanteDTO.setSexo(estudante.getUtilizador().getSexo());
        estudanteDTO.setContacto(estudante.getUtilizador().getContacto());
        estudanteDTO.setUsername(estudante.getUtilizador().getUsername());
        estudanteDTO.setAreaCientifica(estudante.getUtilizador().getAreaCientifica().getDescricao());
        estudanteDTO.setDepartamento(estudante.getUtilizador().getDepartamento().getDescricao());
        estudanteDTO.setCurso(estudante.getCurso().getDescricao());
        estudanteDTO.setNivel(estudante.getNivel());



        return estudanteDTO;
    }

    private Estudante convertToEntity(EstudanteDTO estudanteDTO) {

        Estudante estudante = new Estudante();
        estudante.setId(estudanteDTO.getId());
        estudante.getUtilizador().setNome(estudanteDTO.getNome());
        estudante.getUtilizador().setEmail(estudanteDTO.getEmail());
        estudante.getUtilizador().setSexo(estudanteDTO.getSexo());
        estudante.getUtilizador().setContacto(estudanteDTO.getContacto());
        estudante.getUtilizador().setUsername(estudanteDTO.getUsername());
        estudante.getUtilizador()
                .setAreaCientifica(areaCientificaService.findByDescricao((estudanteDTO.getAreaCientifica())));
        estudante.getUtilizador()
                .setDepartamento(departamentoService.findByDescricao(estudanteDTO.getDepartamento()));
        estudante.setCurso(cursoService.findByDescricao(estudanteDTO.getCurso()));
        estudante.setNivel(estudanteDTO.getNivel());



        return estudante;
    }
}
