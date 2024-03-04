package com.esib.esib.controller;

import com.esib.esib.modelo.Idioma;
import com.esib.esib.modelo.dto.IdiomaDTO;
import com.esib.esib.service.IdiomaService;
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
@RequestMapping("/idiomas")
@RequiredArgsConstructor

public class IdiomaController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(IdiomaController.class.getName());

    /**
     *
     */
    private final IdiomaService idiomaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<IdiomaDTO>> findAll() {
        try {
            var idioma = idiomaService.findAll();
            var idiomaDTO = idioma.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(idiomaDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/idioma/{id}")
    public ResponseEntity<IdiomaDTO> findById(@PathVariable Long id) {
        try {
            var idioma = idiomaService.findById(id);
            return idioma.map(i -> ok(convertToDTO(i)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param idiomaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody IdiomaDTO idiomaDTO) {
        try {
            var newIdioma = idiomaService.create(convertToEntity(idiomaDTO));
            var newIdiomaDTO = convertToDTO(newIdioma);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newIdiomaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param idiomaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody IdiomaDTO idiomaDTO, @PathVariable Long id) {
        try {
            idiomaService.update(convertToEntity(idiomaDTO));
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
            idiomaService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param idioma
     * @return
     */
    private IdiomaDTO convertToDTO(Idioma idioma) {

        var idiomaDTO = new IdiomaDTO();
        idiomaDTO.setId(idioma.getId());
        idiomaDTO.setDescricao(idioma.getDescricao());

        return idiomaDTO;
    }

    /**
     *
     * @param idiomaDTO
     * @return
     */
    private Idioma convertToEntity(IdiomaDTO idiomaDTO) {

        var idioma = new Idioma();
        idioma.setId(idiomaDTO.getId());
        idioma.setDescricao(idiomaDTO.getDescricao());

        return idioma;
    }

}
