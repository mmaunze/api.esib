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

import com.esib.esib.modelo.Cta;
import com.esib.esib.modelo.dto.CtaDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.CargoService;
import com.esib.esib.service.CtaService;
import com.esib.esib.service.DepartamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ctas")
@RequiredArgsConstructor
public class CtaController {

    private final CtaService ctaService;
    private final DepartamentoService departamentoService;
    private final CargoService cargoService;
    private final AreaCientificaService areaCientificaService;

    @GetMapping()
    public ResponseEntity<List<CtaDTO>> findAll() {
        try {
            List<Cta> ctas = ctaService.findAll();
            List<CtaDTO> ctasDTO = ctas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(ctasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cta/{id}")
    public ResponseEntity<CtaDTO> findById(@PathVariable Long id) {
        try {
            Optional<Cta> cta = ctaService.findById(id);
            return cta.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<CtaDTO> findByUsername(@PathVariable String username) {
        try {
            Optional<Cta> cta = ctaService.findCtaPorUsername(username);
            return cta.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacto/{contacto}")
    public ResponseEntity<CtaDTO> findByContacto(@PathVariable String contacto) {
        try {
            Optional<Cta> cta = ctaService.findByContacto(contacto);
            return cta.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CtaDTO> findByEmail(@PathVariable String email) {
        try {
            Optional<Cta> cta = ctaService.findByEmail(email);
            return cta.map(b -> ResponseEntity.ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody CtaDTO ctaDTO) {
        try {
            Cta newCta = ctaService.create(convertToEntity(ctaDTO));
            CtaDTO newCtaDTO = convertToDTO(newCta);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newCtaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody CtaDTO ctaDTO, @PathVariable Long id) {
        try {
            ctaService.update(convertToEntity(ctaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            ctaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Métodos auxiliares para conversão entre Entidade e DTO
     */

    private CtaDTO convertToDTO(Cta cta) {

        CtaDTO ctaDTO = new CtaDTO();

        ctaDTO.setId(cta.getUtilizador().getId());
        ctaDTO.setNome(cta.getUtilizador().getNome());
        ctaDTO.setEmail(cta.getUtilizador().getEmail());
        ctaDTO.setSexo(cta.getUtilizador().getSexo());
        ctaDTO.setContacto(cta.getUtilizador().getContacto());
        ctaDTO.setUsername(cta.getUtilizador().getUsername());
        ctaDTO.setAreaCientifica(cta.getUtilizador().getAreaCientifica().getDescricao());
        ctaDTO.setDepartamento(cta.getUtilizador().getDepartamento().getDescricao());
        ctaDTO.setCargo(cta.getCargo().getDescricao());

        return ctaDTO;
    }

    private Cta convertToEntity(CtaDTO ctaDTO) {

        Cta cta = new Cta();
        cta.setId(ctaDTO.getId());
        cta.getUtilizador().setNome(ctaDTO.getNome());
        cta.getUtilizador().setEmail(ctaDTO.getEmail());
        cta.getUtilizador().setSexo(ctaDTO.getSexo());
        cta.getUtilizador().setContacto(ctaDTO.getContacto());
        cta.getUtilizador().setUsername(ctaDTO.getUsername());
        cta.getUtilizador()
                .setAreaCientifica(areaCientificaService.findByDescricao((ctaDTO.getAreaCientifica())));
        cta.getUtilizador()
                .setDepartamento(departamentoService.findByDescricao(ctaDTO.getDepartamento()));
        cta.setCargo(cargoService.findByDescricao(ctaDTO.getCargo()));

        return cta;
    }
}
