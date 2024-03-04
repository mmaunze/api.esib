package com.esib.esib.controller;

import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.dto.EstadoDTO;
import com.esib.esib.service.EstadoService;
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
@RequestMapping("/estados")
@RequiredArgsConstructor

public class EstadoController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(EstadoController.class.getName());

    /**
     *
     */
    private final EstadoService estadoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<EstadoDTO>> findAll() {
        try {
            var estado = estadoService.findAll();
            var estadoDTO = estado.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(estadoDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/estado/{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
        try {
            var estado = estadoService.findById(id);
            return estado.map(e -> ok(convertToDTO(e)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param estadoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EstadoDTO estadoDTO) {
        try {
            var newEstado = estadoService.create(convertToEntity(estadoDTO));
            var newEstadoDTO = convertToDTO(newEstado);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newEstadoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param estadoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody EstadoDTO estadoDTO, @PathVariable Long id) {
        try {
            estadoService.update(convertToEntity(estadoDTO));
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
            estadoService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param estado
     * @return
     */
    private EstadoDTO convertToDTO(Estado estado) {

        var estadoDTO = new EstadoDTO();
        estadoDTO.setId(estado.getId());
        estadoDTO.setDescricao(estado.getDescricao());

        return estadoDTO;
    }

    /**
     *
     * @param estadoDTO
     * @return
     */
    private Estado convertToEntity(EstadoDTO estadoDTO) {

        var estado = new Estado();
        estado.setId(estadoDTO.getId());
        estado.setDescricao(estadoDTO.getDescricao());

        return estado;
    }

}
