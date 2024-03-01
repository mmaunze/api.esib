package com.esib.esib.controller;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.dto.AreaCientificaDTO;
import com.esib.esib.service.AreaCientificaService;
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
@RequestMapping("/areascientificas")
@RequiredArgsConstructor

public class AreaCientificaController {
    private final AreaCientificaService areaCientificaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<AreaCientificaDTO>> findAll() {
        try {
            var areaCientifica = areaCientificaService.findAll();
            var areaCientificaDTO = areaCientifica.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(areaCientificaDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/area/{id}")
    public ResponseEntity<AreaCientificaDTO> findById(@PathVariable Long id) {
        try {
            var areaCientifica = areaCientificaService.findById(id);
            return areaCientifica.map(a -> ok(convertToDTO(a)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param areaCientificaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody AreaCientificaDTO areaCientificaDTO) {
        try {
            var newAreaCientifica = areaCientificaService.create(convertToEntity(areaCientificaDTO));
            var newAreaCientificaDTO = convertToDTO(newAreaCientifica);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newAreaCientificaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param areaCientificaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody AreaCientificaDTO areaCientificaDTO, @PathVariable Long id) {
        try {
            areaCientificaService.update(id, convertToEntity(areaCientificaDTO));
            return noContent().build();
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
            areaCientificaService.delete(id);
            return noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private AreaCientificaDTO convertToDTO(AreaCientifica areaCientifica) {

        var areaCientificaDTO = new AreaCientificaDTO();
        areaCientificaDTO.setId(areaCientifica.getId());
        areaCientificaDTO.setDescricao(areaCientifica.getDescricao());

        return areaCientificaDTO;
    }

    private AreaCientifica convertToEntity(AreaCientificaDTO areaCientificaDTO) {

        var areaCientifica = new AreaCientifica();
        areaCientifica.setId(areaCientificaDTO.getId());
        areaCientifica.setDescricao(areaCientificaDTO.getDescricao());

        return areaCientifica;
    }
    private static final Logger LOG = Logger.getLogger(AreaCientificaController.class.getName());
}
