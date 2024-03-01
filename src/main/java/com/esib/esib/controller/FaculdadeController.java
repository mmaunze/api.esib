package com.esib.esib.controller;

import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.dto.FaculdadeDTO;
import com.esib.esib.service.FaculdadeService;
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
@RequestMapping("/faculdades")
@RequiredArgsConstructor

public class FaculdadeController {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(FaculdadeController.class.getName());

    /**
     *
     */
    private final FaculdadeService faculdadeService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<FaculdadeDTO>> findAll() {
        try {
            var faculdade = faculdadeService.findAll();
            var faculdadeDTO = faculdade.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(faculdadeDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/faculdade/{id}")
    public ResponseEntity<FaculdadeDTO> findById(@PathVariable Long id) {
        try {
            var faculdade = faculdadeService.findById(id);
            return faculdade.map(f -> ok(convertToDTO(f)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param faculdadeDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody FaculdadeDTO faculdadeDTO) {
        try {
            var newFaculdade = faculdadeService.create(convertToEntity(faculdadeDTO));
            var newFaculdadeDTO = convertToDTO(newFaculdade);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newFaculdadeDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param faculdadeDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody FaculdadeDTO faculdadeDTO, @PathVariable Long id) {
        try {
            faculdadeService.update(convertToEntity(faculdadeDTO));
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
            faculdadeService.delete(id);
            return noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO

    /**
     *
     * @param faculdade
     * @return
     */
    private FaculdadeDTO convertToDTO(Faculdade faculdade) {

        var faculdadeDTO = new FaculdadeDTO();
        faculdadeDTO.setId(faculdade.getId());
        faculdadeDTO.setDescricao(faculdade.getDescricao());
        faculdadeDTO.setSigla(faculdade.getSigla());


        return faculdadeDTO;
    }

    /**
     *
     * @param faculdadeDTO
     * @return
     */
    private Faculdade convertToEntity(FaculdadeDTO faculdadeDTO) {

        var faculdade = new Faculdade();
        faculdade.setId(faculdadeDTO.getId());
        faculdade.setDescricao(faculdadeDTO.getDescricao());
        faculdade.setSigla(faculdadeDTO.getSigla());

        return faculdade;
    }

}
