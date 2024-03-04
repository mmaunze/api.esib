package com.esib.esib.controller;

import com.esib.esib.modelo.TipoObra;
import com.esib.esib.modelo.dto.TipoObraDTO;
import com.esib.esib.service.TipoObraService;
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
@RequestMapping("/tiposobras")
@RequiredArgsConstructor

public class TipoObraController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoObraController.class.getName());

    /**
     *
     */
    private final TipoObraService tipoObraService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<TipoObraDTO>> findAll() {
        try {
            var tipoObra = tipoObraService.findAll();
            var tipoObraDTO = tipoObra.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(tipoObraDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/tipo/{id}")
    public ResponseEntity<TipoObraDTO> findById(@PathVariable Long id) {
        try {
            var tipoObra = tipoObraService.findById(id);
            return tipoObra.map(to -> ok(convertToDTO(to)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoObraDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoObraDTO tipoObraDTO) {
        try {
            var newtipoObra = tipoObraService.create(convertToEntity(tipoObraDTO));
            var newtipoObraDTO = convertToDTO(newtipoObra);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newtipoObraDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoObraDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoObraDTO tipoObraDTO, @PathVariable Long id) {
        try {
            tipoObraService.update(convertToEntity(tipoObraDTO));
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
            tipoObraService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param tipoObra
     * @return
     */
    private TipoObraDTO convertToDTO(TipoObra tipoObra) {

        var tipoObraDTO = new TipoObraDTO();
        tipoObraDTO.setId(tipoObra.getId());
        tipoObraDTO.setDescricao(tipoObra.getDescricao());

        return tipoObraDTO;
    }

    /**
     *
     * @param tipoObraDTO
     * @return
     */
    private TipoObra convertToEntity(TipoObraDTO tipoObraDTO) {

        var tipoObra = new TipoObra();
        tipoObra.setId(tipoObraDTO.getId());
        tipoObra.setDescricao(tipoObraDTO.getDescricao());

        return tipoObra;
    }

}
