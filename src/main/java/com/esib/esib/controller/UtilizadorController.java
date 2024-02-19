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

import com.esib.esib.dto.UtilizadorDTO;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.service.UtilizadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilizadores")
@RequiredArgsConstructor
public class UtilizadorController {

    private final UtilizadorService utilizadorService;

    @GetMapping()
    public ResponseEntity<List<UtilizadorDTO>> findAll() {
        try {
            List<Utilizador> utilizadores = utilizadorService.findAll();
            List<UtilizadorDTO> utilizadoresDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(utilizadoresDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilizadorDTO> findById(@PathVariable Long id) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.buscarUtilizadorPorId(id);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UtilizadorDTO> findByUsername(@PathVariable String username) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.buscarUtilizadorPorUsername(username);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("contacto/{contacto}")
    public ResponseEntity<UtilizadorDTO> findByContacto(@PathVariable String contacto) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.buscarUtilizadorPorContacto(contacto);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UtilizadorDTO> findByEmail(@PathVariable String email) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.buscarUtilizadorPorEmail(email);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<UtilizadorDTO> create(@RequestBody UtilizadorDTO utilizadorDTO) {
        try {
            Utilizador novoUtilizador = utilizadorService.create(convertToEntity(utilizadorDTO));
            UtilizadorDTO novoUtilizadorDTO = convertToDTO(novoUtilizador);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoUtilizadorDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).body(novoUtilizadorDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UtilizadorDTO> update(@RequestBody UtilizadorDTO utilizadorDTO, @PathVariable Long id) {
        try {
            Utilizador utilizadorAtualizado = utilizadorService.update(convertToEntity(utilizadorDTO));
            return new ResponseEntity<>(convertToDTO(utilizadorAtualizado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            utilizadorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private UtilizadorDTO convertToDTO(Utilizador utilizador) {
        // Implemente a lógica de conversão de Utilizador para UtilizadorDTO
        // Exemplo:
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO();
        utilizadorDTO.setId(utilizador.getId());
        utilizadorDTO.setNome(utilizador.getNome());
        utilizadorDTO.setEmail(utilizador.getEmail());
        utilizadorDTO.setSexo(utilizador.getSexo());
        utilizadorDTO.setContacto(utilizador.getContacto());
        utilizadorDTO.setUsername(utilizador.getUsername());

        // utilizadorDTO.setAreaCientifica(utilizador.getIdArea());
        // utilizadorDTO.setDepartamento(utilizador.getIdDepartamento());

        return utilizadorDTO;
    }

    private Utilizador convertToEntity(UtilizadorDTO utilizadorDTO) {
        // Implemente a lógica de conversão de UtilizadorDTO para Utilizador
        // Exemplo:
        Utilizador utilizador = new Utilizador();
        utilizador.setId(utilizadorDTO.getId());
        utilizador.setNome(utilizadorDTO.getNome());
        utilizador.setEmail(utilizadorDTO.getEmail());
        utilizador.setSexo(utilizadorDTO.getSexo());
        utilizador.setContacto(utilizadorDTO.getContacto());
        utilizador.setUsername(utilizadorDTO.getUsername());
        // utilizador.setIdArea(utilizadorDTO.getAreaCientifica());
        // utilizador.setIdDepartamento(utilizadorDTO.getDepartamento());
        // Continue para outros campos...
        return utilizador;
    }
}
