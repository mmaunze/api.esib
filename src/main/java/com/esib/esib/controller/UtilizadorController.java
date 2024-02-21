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

import com.esib.esib.modelo.Utilizador;
import com.esib.esib.modelo.dto.UtilizadorDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.DepartamentoService;
import com.esib.esib.service.TipoUtilizadorService;
import com.esib.esib.service.UtilizadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilizadores")
@RequiredArgsConstructor
public class UtilizadorController {

    private final UtilizadorService utilizadorService;
    private final DepartamentoService departamentoService;
    private final TipoUtilizadorService tipoUtilizadorService;
    private final AreaCientificaService areaCientificaService;

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

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<UtilizadorDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Utilizador> utilizadores = utilizadorService.findByAreaCientifica(areacientifica);
            List<UtilizadorDTO> obrasDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(obrasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<UtilizadorDTO>> findByDepartamento(@PathVariable String departamento) {
        try {
            List<Utilizador> utilizadores = utilizadorService.findByDepartamento(departamento);
            List<UtilizadorDTO> utilizadoresDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(utilizadoresDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipoutilizador/{tipoutilizador}")
    public ResponseEntity<List<UtilizadorDTO>> findByTipoUtilizador(@PathVariable String tipoutilizador) {
        try {
            List<Utilizador> utilizadores = utilizadorService.findByTipoUtilizador(tipoutilizador);
            List<UtilizadorDTO> utilizadoresDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(utilizadoresDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/utilizador/{id}")
    public ResponseEntity<UtilizadorDTO> findById(@PathVariable Long id) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.findById(id);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UtilizadorDTO> findByUsername(@PathVariable String username) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.findUtilizadorPorUsername(username);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacto/{contacto}")
    public ResponseEntity<UtilizadorDTO> findByContacto(@PathVariable String contacto) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.findUtilizadorPorContacto(contacto);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UtilizadorDTO> findByEmail(@PathVariable String email) {
        try {
            Optional<Utilizador> utilizador = utilizadorService.findUtilizadorPorEmail(email);
            return utilizador.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody UtilizadorDTO utilizadorDTO) {
        try {
            Utilizador newUtilizador = utilizadorService.create(convertToEntity(utilizadorDTO));
            UtilizadorDTO newUtilizadorDTO = convertToDTO(newUtilizador);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newUtilizadorDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody UtilizadorDTO utilizadorDTO, @PathVariable Long id) {
        try {
            utilizadorService.update(convertToEntity(utilizadorDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            utilizadorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Métodos auxiliares para conversão entre Entidade e DTO
     */

    private UtilizadorDTO convertToDTO(Utilizador utilizador) {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO();
        utilizadorDTO.setId(utilizador.getId());
        utilizadorDTO.setNome(utilizador.getNome());
        utilizadorDTO.setEmail(utilizador.getEmail());
        utilizadorDTO.setSexo(utilizador.getSexo());
        utilizadorDTO.setContacto(utilizador.getContacto());
        utilizadorDTO.setUsername(utilizador.getUsername());
        utilizadorDTO.setAreaCientifica(utilizador.getAreaCientifica().getDescricao());
        utilizadorDTO.setDepartamento(utilizador.getDepartamento().getDescricao());
        utilizadorDTO.setTipoUtilizador(utilizador.getTipoUtilizador().getDescricao());

        return utilizadorDTO;
    }

    private Utilizador convertToEntity(UtilizadorDTO utilizadorDTO) {

        Utilizador utilizador = new Utilizador();
        utilizador.setId(utilizadorDTO.getId());
        utilizador.setNome(utilizadorDTO.getNome());
        utilizador.setEmail(utilizadorDTO.getEmail());
        utilizador.setSexo(utilizadorDTO.getSexo());
        utilizador.setContacto(utilizadorDTO.getContacto());
        utilizador.setUsername(utilizadorDTO.getUsername());
        utilizador.setAreaCientifica(areaCientificaService.findByDescricao((utilizadorDTO.getAreaCientifica())));
        utilizador.setDepartamento(departamentoService.findByDescricao(utilizadorDTO.getDepartamento()));
        utilizador.setTipoUtilizador(tipoUtilizadorService.findByDescricao(utilizadorDTO.getTipoUtilizador()));

        return utilizador;
    }
}
