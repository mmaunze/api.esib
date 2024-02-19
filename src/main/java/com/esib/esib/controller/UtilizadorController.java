package com.esib.esib.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.esib.esib.modelo.Utilizador; // Importe a classe Utilizador
import com.esib.esib.service.UtilizadorService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilizadores")
@RequiredArgsConstructor // Substitui @Autowired
public class UtilizadorController {

    private final UtilizadorService utilizadorService; // Injetando UtilizadorService

    @GetMapping()
    public ResponseEntity<List<Utilizador>> findAll() {
        try {
            List<Utilizador> utilizadores = utilizadorService.findAll();
            return new ResponseEntity<>(utilizadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonIgnore
    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> find(@PathVariable Long id) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.buscarUtilizadorPorId(id);
            return utilizador.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Utilizador> create(@RequestBody Utilizador utilizador) {
        try {
            Utilizador novoUtilizador = utilizadorService.create(utilizador);

            // Construir a URI do novo recurso
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoUtilizador.getIdUtilizador())
                    .toUri();

            return ResponseEntity.created(location).body(novoUtilizador);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Utilizador> update(@RequestBody Utilizador utilizador) {
        try {
            Utilizador utilizadorAtualizado = utilizadorService.update(utilizador);
            return new ResponseEntity<>(utilizadorAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            utilizadorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
