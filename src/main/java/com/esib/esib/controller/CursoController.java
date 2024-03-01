package com.esib.esib.controller;

import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.dto.CursoDTO;
import com.esib.esib.service.CursoService;
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
@RequestMapping("/cursos")
@RequiredArgsConstructor

public class CursoController {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(CursoController.class.getName());

    /**
     *
     */
    private final CursoService cursoService;

    /**
     *
     */
    private final FaculdadeService faculdadeService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<CursoDTO>> findAll() {
        try {
            var curso = cursoService.findAll();
            var cursoDTO = curso.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(cursoDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/curso/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        try {
            var curso = cursoService.findById(id);
            return curso.map(c -> ok(convertToDTO(c)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param cursoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody CursoDTO cursoDTO) {
        try {
            var newCurso = cursoService.create(convertToEntity(cursoDTO));
            var newCursoDTO = convertToDTO(newCurso);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newCursoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param cursoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody CursoDTO cursoDTO, @PathVariable Long id) {
        try {
            cursoService.update(convertToEntity(cursoDTO));
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
            cursoService.delete(id);
            return noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO

    /**
     *
     * @param curso
     * @return
     */
    private CursoDTO convertToDTO(Curso curso) {

        var cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setDescricao(curso.getDescricao());
        cursoDTO.setFaculdade(curso.getFaculdade().getDescricao());

        return cursoDTO;
    }

    /**
     *
     * @param cursoDTO
     * @return
     */
    private Curso convertToEntity(CursoDTO cursoDTO) {

        var curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setDescricao(cursoDTO.getDescricao());
        curso.setFaculdade(faculdadeService.findByDescricao(cursoDTO.getFaculdade()));

        return curso;
    }

}
