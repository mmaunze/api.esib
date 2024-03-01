package com.esib.esib.controller;

import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.dto.TipoUtilizadorDTO;
import com.esib.esib.service.TipoUtilizadorService;
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
@RequestMapping("/tiposutilizadores")
@RequiredArgsConstructor

public class TipoUtilizadorController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoUtilizadorController.class.getName());

    /**
     *
     */
    private final TipoUtilizadorService tipoUtilizadorService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<TipoUtilizadorDTO>> findAll() {
        try {
            var tipoUtilizador = tipoUtilizadorService.findAll();
            var tipoUtilizadorDTO = tipoUtilizador.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(tipoUtilizadorDTO, OK);
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
    public ResponseEntity<TipoUtilizadorDTO> findById(@PathVariable Long id) {
        try {
            var tipoUtilizador = tipoUtilizadorService.findById(id);
            return tipoUtilizador.map(to -> ok(convertToDTO(to)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoUtilizadorDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoUtilizadorDTO tipoUtilizadorDTO) {
        try {
            var newtipoUtilizador = tipoUtilizadorService.create(convertToEntity(tipoUtilizadorDTO));
            var newtipoUtilizadorDTO = convertToDTO(newtipoUtilizador);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newtipoUtilizadorDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoUtilizadorDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoUtilizadorDTO tipoUtilizadorDTO, @PathVariable Long id) {
        try {
            tipoUtilizadorService.update(convertToEntity(tipoUtilizadorDTO));
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
            tipoUtilizadorService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param tipoUtilizador
     * @return
     */
    private TipoUtilizadorDTO convertToDTO(TipoUtilizador tipoUtilizador) {

        var tipoUtilizadorDTO = new TipoUtilizadorDTO();
        tipoUtilizadorDTO.setId(tipoUtilizador.getId());
        tipoUtilizadorDTO.setDescricao(tipoUtilizador.getDescricao());

        return tipoUtilizadorDTO;
    }

    /**
     *
     * @param tipoUtilizadorDTO
     * @return
     */
    private TipoUtilizador convertToEntity(TipoUtilizadorDTO tipoUtilizadorDTO) {

        var tipoUtilizador = new TipoUtilizador();
        tipoUtilizador.setId(tipoUtilizadorDTO.getId());
        tipoUtilizador.setDescricao(tipoUtilizadorDTO.getDescricao());

        return tipoUtilizador;
    }

}
