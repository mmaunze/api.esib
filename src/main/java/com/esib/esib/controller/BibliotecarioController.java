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

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.dto.BibliotecarioDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.DepartamentoService;
import com.esib.esib.service.FaculdadeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bibliotecarios")
@RequiredArgsConstructor
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;
    private final DepartamentoService departamentoService;
    private final FaculdadeService faculdadeService;
    private final AreaCientificaService areaCientificaService;

    @GetMapping()
    public ResponseEntity<List<BibliotecarioDTO>> findAll() {
        try {
            List<Bibliotecario> bibliotecarios = bibliotecarioService.findAll();
            List<BibliotecarioDTO> bibliotecariosDTO = bibliotecarios.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(bibliotecariosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bibliotecario/{id}")
    public ResponseEntity<BibliotecarioDTO> findById(@PathVariable Long id) {
        try {
            Optional<Bibliotecario> bibliotecario = bibliotecarioService.findById(id);
            return bibliotecario.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BibliotecarioDTO> findByUsername(@PathVariable String username) {
        try {
            Optional<Bibliotecario> bibliotecario = bibliotecarioService.findBibliotecarioPorUsername(username);
            return bibliotecario.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacto/{contacto}")
    public ResponseEntity<BibliotecarioDTO> findByContacto(@PathVariable String contacto) {
        try {
            Optional<Bibliotecario> bibliotecario = bibliotecarioService.findBibliotecarioPorContacto(contacto);
            return bibliotecario.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BibliotecarioDTO> findByEmail(@PathVariable String email) {
        try {
            Optional<Bibliotecario> bibliotecario = bibliotecarioService.findBibliotecarioPorEmail(email);
            return bibliotecario.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody BibliotecarioDTO bibliotecarioDTO) {
        try {
            Bibliotecario newBibliotecario = bibliotecarioService.create(convertToEntity(bibliotecarioDTO));
            BibliotecarioDTO newBibliotecarioDTO = convertToDTO(newBibliotecario);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newBibliotecarioDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody BibliotecarioDTO bibliotecarioDTO, @PathVariable Long id) {
        try {
            bibliotecarioService.update(convertToEntity(bibliotecarioDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            bibliotecarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Métodos auxiliares para conversão entre Entidade e DTO
     */

    private BibliotecarioDTO convertToDTO(Bibliotecario bibliotecario) {

        BibliotecarioDTO bibliotecarioDTO = new BibliotecarioDTO();

        bibliotecarioDTO.setId(bibliotecario.getUtilizador().getId());
        bibliotecarioDTO.setNome(bibliotecario.getUtilizador().getNome());
        bibliotecarioDTO.setEmail(bibliotecario.getUtilizador().getEmail());
        bibliotecarioDTO.setSexo(bibliotecario.getUtilizador().getSexo());
        bibliotecarioDTO.setContacto(bibliotecario.getUtilizador().getContacto());
        bibliotecarioDTO.setUsername(bibliotecario.getUtilizador().getUsername());
        bibliotecarioDTO.setAreaCientifica(bibliotecario.getUtilizador().getAreaCientifica().getDescricao());
        bibliotecarioDTO.setDepartamento(bibliotecario.getUtilizador().getDepartamento().getDescricao());
        bibliotecarioDTO.setFaculdade(bibliotecario.getFaculdade().getDescricao());

        return bibliotecarioDTO;
    }

    private Bibliotecario convertToEntity(BibliotecarioDTO bibliotecarioDTO) {

        Bibliotecario bibliotecario = new Bibliotecario();
        bibliotecario.setId(bibliotecarioDTO.getId());
        bibliotecario.getUtilizador().setNome(bibliotecarioDTO.getNome());
        bibliotecario.getUtilizador().setEmail(bibliotecarioDTO.getEmail());
        bibliotecario.getUtilizador().setSexo(bibliotecarioDTO.getSexo());
        bibliotecario.getUtilizador().setContacto(bibliotecarioDTO.getContacto());
        bibliotecario.getUtilizador().setUsername(bibliotecarioDTO.getUsername());
        bibliotecario.getUtilizador()
                .setAreaCientifica(areaCientificaService.findByDescricao((bibliotecarioDTO.getAreaCientifica())));
        bibliotecario.getUtilizador()
                .setDepartamento(departamentoService.findByDescricao(bibliotecarioDTO.getDepartamento()));
        bibliotecario.setFaculdade(faculdadeService.findByDescricao(bibliotecarioDTO.getFaculdade()));

        return bibliotecario;
    }
}
