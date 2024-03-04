package com.esib.esib.controller;

import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.dto.EstudanteDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.CursoService;
import com.esib.esib.service.DepartamentoService;
import com.esib.esib.service.EstudanteService;
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
@RequestMapping("/utilizadores/estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(EstudanteController.class.getName());

    /**
     *
     */
    private final EstudanteService estudanteService;

    /**
     *
     */
    private final DepartamentoService departamentoService;

    /**
     *
     */
    private final CursoService cursoService;

    /**
     *
     */
    private final AreaCientificaService areaCientificaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<EstudanteDTO>> findAll() {
        try {
            var estudantes = estudanteService.findAll();
            var estudantesDTO = estudantes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(estudantesDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/estudante/{id}")
    public ResponseEntity<EstudanteDTO> findById(@PathVariable Long id) {
        try {
            var estudante = estudanteService.findById(id);
            return estudante.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<EstudanteDTO> findByUsername(@PathVariable String username) {
        try {
            var estudante = estudanteService.findByUsername(username);
            return estudante.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param contacto
     * @return
     */
    @GetMapping("/contacto/{contacto}")
    public ResponseEntity<EstudanteDTO> findByContacto(@PathVariable String contacto) {
        try {
            var estudante = estudanteService.findByContacto(contacto);
            return estudante.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<EstudanteDTO> findByEmail(@PathVariable String email) {
        try {
            var estudante = estudanteService.findByEmail(email);
            return estudante.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param estudanteDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EstudanteDTO estudanteDTO) {
        try {
            var newEstudante = estudanteService.create(convertToEntity(estudanteDTO));
            var newEstudanteDTO = convertToDTO(newEstudante);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newEstudanteDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param estudanteDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody EstudanteDTO estudanteDTO, @PathVariable Long id) {
        try {
            estudanteService.update(convertToEntity(estudanteDTO));
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
            estudanteService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Métodos auxiliares para conversão entre Entidade e DTO
     */
    /**
     *
     * @param estudante
     * @return
     */
    private EstudanteDTO convertToDTO(Estudante estudante) {

        var estudanteDTO = new EstudanteDTO();

        estudanteDTO.setId(estudante.getUtilizador().getId());
        estudanteDTO.setNome(estudante.getUtilizador().getNome());
        estudanteDTO.setEmail(estudante.getUtilizador().getEmail());
        estudanteDTO.setSexo(estudante.getUtilizador().getSexo());
        estudanteDTO.setContacto(estudante.getUtilizador().getContacto());
        estudanteDTO.setUsername(estudante.getUtilizador().getUsername());
        estudanteDTO.setAreaCientifica(estudante.getUtilizador().getAreaCientifica().getDescricao());
        estudanteDTO.setDepartamento(estudante.getUtilizador().getDepartamento().getDescricao());
        estudanteDTO.setCurso(estudante.getCurso().getDescricao());
        estudanteDTO.setNivel(estudante.getNivel());

        return estudanteDTO;
    }

    /**
     *
     * @param estudanteDTO
     * @return
     */
    private Estudante convertToEntity(EstudanteDTO estudanteDTO) {

        var estudante = new Estudante();
        estudante.setId(estudanteDTO.getId());
        estudante.getUtilizador().setNome(estudanteDTO.getNome());
        estudante.getUtilizador().setEmail(estudanteDTO.getEmail());
        estudante.getUtilizador().setSexo(estudanteDTO.getSexo());
        estudante.getUtilizador().setContacto(estudanteDTO.getContacto());
        estudante.getUtilizador().setUsername(estudanteDTO.getUsername());
        estudante.getUtilizador()
                .setAreaCientifica(areaCientificaService.findByDescricao((estudanteDTO.getAreaCientifica())));
        estudante.getUtilizador()
                .setDepartamento(departamentoService.findByDescricao(estudanteDTO.getDepartamento()));
        estudante.setCurso(cursoService.findByDescricao(estudanteDTO.getCurso()));
        estudante.setNivel(estudanteDTO.getNivel());

        return estudante;
    }

}
