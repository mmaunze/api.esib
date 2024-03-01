package com.esib.esib.controller;

import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.dto.DepartamentoDTO;
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
@RequestMapping("/departamentos")
@RequiredArgsConstructor

public class DepartamentoController {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(DepartamentoController.class.getName());

    /**
     *
     */
    private final DepartamentoService departamentoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<DepartamentoDTO>> findAll() {
        try {
            var departamento = departamentoService.findAll();
            var departamentoDTO = departamento.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(departamentoDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/departamento/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        try {
            var departamento = departamentoService.findById(id);
            return departamento.map(d -> ok(convertToDTO(d)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param departamentoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            var newDepartamento = departamentoService.create(convertToEntity(departamentoDTO));
            var newDepartamentoDTO = convertToDTO(newDepartamento);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newDepartamentoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param departamentoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody DepartamentoDTO departamentoDTO, @PathVariable Long id) {
        try {
            departamentoService.update(convertToEntity(departamentoDTO));
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
            departamentoService.delete(id);
            return noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO

    /**
     *
     * @param departamento
     * @return
     */
    private DepartamentoDTO convertToDTO(Departamento departamento) {

        var departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(departamento.getId());
        departamentoDTO.setDescricao(departamento.getDescricao());
        departamentoDTO.setSigla(departamento.getSigla());


        return departamentoDTO;
    }

    /**
     *
     * @param departamentoDTO
     * @return
     */
    private Departamento convertToEntity(DepartamentoDTO departamentoDTO) {

        var departamento = new Departamento();
        departamento.setId(departamentoDTO.getId());
        departamento.setDescricao(departamentoDTO.getDescricao());
        departamento.setSigla(departamentoDTO.getSigla());

        return departamento;
    }

}
