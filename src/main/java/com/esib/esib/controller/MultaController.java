package com.esib.esib.controller;

import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.dto.MultaDTO;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.MultaService;
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
@RequestMapping("/multas")
@RequiredArgsConstructor

public class MultaController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(MultaController.class.getName());

    /**
     *
     */
    private final MultaService multaService;

    /**
     *
     */
    private final EstadoService estadoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<MultaDTO>> findAll() {
        try {
            var multas = multaService.findAll();
            var multaDTO = multas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(multaDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/multa/{id}")
    public ResponseEntity<MultaDTO> findById(@PathVariable Long id) {
        try {
            var multa = multaService.findById(id);
            return multa.map(d -> ok(convertToDTO(d)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param estado
     * @return
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MultaDTO>> findByEstado(@PathVariable String estado) {
        try {
            var multas = multaService.findByEstado(estado);
            var multaDTO = multas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(multaDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param multaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MultaDTO multaDTO) {
        try {
            var newMulta = multaService.create(convertToEntity(multaDTO));
            var newMultaDTO = convertToDTO(newMulta);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newMultaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param multaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody MultaDTO multaDTO, @PathVariable Long id) {
        try {
            multaService.update(convertToEntity(multaDTO));
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
            multaService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param multa
     * @return
     */
    private MultaDTO convertToDTO(Multa multa) {

        var multaDTO = new MultaDTO();
        multaDTO.setId(multa.getId());
        multaDTO.setValor(multa.getValorMulta());
        multaDTO.setEmprestimo(multa.getEmprestimo().getId());
        multaDTO.setEstado(multa.getEstado().getDescricao());

        return multaDTO;
    }

    /**
     *
     * @param multaDTO
     * @return
     */
    private Multa convertToEntity(MultaDTO multaDTO) {

        var multa = new Multa();
        multa.setId(multaDTO.getId());
        multa.setValorMulta(multaDTO.getValor());
        multa.getEmprestimo().setId(multaDTO.getId());
        multa.setEstado(estadoService.findByDescricao(multaDTO.getEstado()));

        return multa;
    }

}
