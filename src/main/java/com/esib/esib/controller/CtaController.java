package com.esib.esib.controller;

import com.esib.esib.modelo.Cta;
import com.esib.esib.modelo.dto.CtaDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.CargoService;
import com.esib.esib.service.CtaService;
import com.esib.esib.service.DepartamentoService;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

/**
 *
 * @author Meldo Maunze
 */
@RestController
@RequestMapping("/ctas")
@RequiredArgsConstructor
public class CtaController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(CtaController.class.getName());

    /**
     *
     */
    private final CtaService ctaService;

    /**
     *
     */
    private final DepartamentoService departamentoService;

    /**
     *
     */
    private final CargoService cargoService;

    /**
     *
     */
    private final AreaCientificaService areaCientificaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<CtaDTO>> findAll() {
        try {
            var ctas = ctaService.findAll();
            var ctasDTO = ctas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(ctasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/cta/{id}")
    public ResponseEntity<CtaDTO> findById(@PathVariable Long id) {
        try {
            var cta = ctaService.findById(id);
            return cta.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<CtaDTO> findByUsername(@PathVariable String username) {
        try {
            var cta = ctaService.findCtaPorUsername(username);
            return cta.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param contacto
     * @return
     */
    @GetMapping("/contacto/{contacto}")
    public ResponseEntity<CtaDTO> findByContacto(@PathVariable String contacto) {
        try {
            var cta = ctaService.findByContacto(contacto);
            return cta.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<CtaDTO> findByEmail(@PathVariable String email) {
        try {
            var cta = ctaService.findByEmail(email);
            return cta.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param ctaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody CtaDTO ctaDTO) {
        try {
            var newCta = ctaService.create(convertToEntity(ctaDTO));
            var newCtaDTO = convertToDTO(newCta);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newCtaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param ctaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody CtaDTO ctaDTO, @PathVariable Long id) {
        try {
            ctaService.update(convertToEntity(ctaDTO));
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            ctaService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Métodos auxiliares para conversão entre Entidade e DTO
     */
    /**
     *
     * @param cta
     * @return
     */
    private CtaDTO convertToDTO(Cta cta) {

        var ctaDTO = new CtaDTO();

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

    /**
     *
     * @param ctaDTO
     * @return
     */
    private Cta convertToEntity(CtaDTO ctaDTO) {

        var cta = new Cta();
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
