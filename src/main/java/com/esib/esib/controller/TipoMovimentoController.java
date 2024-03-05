package com.esib.esib.controller;

import com.esib.esib.model.TipoMovimento;
import com.esib.esib.model.dto.TipoMovimentoDTO;
import com.esib.esib.service.TipoMovimentoService;
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
@RequestMapping("/tiposmovimentos")
@RequiredArgsConstructor

public class TipoMovimentoController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoMovimentoController.class.getName());

    /**
     *
     */
    private final TipoMovimentoService tipoMovimentoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<TipoMovimentoDTO>> findAll() {
        try {
            var tipoMovimento = tipoMovimentoService.findAll();
            var tipoMovimentoDTO = tipoMovimento.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(tipoMovimentoDTO, OK);
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
    public ResponseEntity<TipoMovimentoDTO> findById(@PathVariable Long id) {
        try {
            var tipoMovimento = tipoMovimentoService.findById(id);
            return tipoMovimento.map(a -> ok(convertToDTO(a)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoMovimentoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody TipoMovimentoDTO tipoMovimentoDTO) {
        try {
            var newtipoMovimento = tipoMovimentoService.create(convertToEntity(tipoMovimentoDTO));
            var newtipoMovimentoDTO = convertToDTO(newtipoMovimento);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newtipoMovimentoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoMovimentoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody TipoMovimentoDTO tipoMovimentoDTO, @PathVariable Long id) {
        try {
            tipoMovimentoService.update(convertToEntity(tipoMovimentoDTO));
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
            tipoMovimentoService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param tipoMovimento
     * @return
     */
    private TipoMovimentoDTO convertToDTO(TipoMovimento tipoMovimento) {

        var tipoMovimentoDTO = new TipoMovimentoDTO();
        tipoMovimentoDTO.setId(tipoMovimento.getId());
        tipoMovimentoDTO.setDescricao(tipoMovimento.getDescricao());

        return tipoMovimentoDTO;
    }

    /**
     *
     * @param tipoMovimentoDTO
     * @return
     */
    private TipoMovimento convertToEntity(TipoMovimentoDTO tipoMovimentoDTO) {

        var tipoMovimento = new TipoMovimento();
        tipoMovimento.setId(tipoMovimentoDTO.getId());
        tipoMovimento.setDescricao(tipoMovimentoDTO.getDescricao());

        return tipoMovimento;
    }

}
