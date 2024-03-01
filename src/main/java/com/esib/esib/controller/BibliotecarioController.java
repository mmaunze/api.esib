package com.esib.esib.controller;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.dto.BibliotecarioDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.DepartamentoService;
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
@RequestMapping("/bibliotecarios")
@RequiredArgsConstructor
public class BibliotecarioController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(BibliotecarioController.class.getName());

    /**
     *
     */
    private final BibliotecarioService bibliotecarioService;

    /**
     *
     */
    private final DepartamentoService departamentoService;

    /**
     *
     */
    private final FaculdadeService faculdadeService;

    /**
     *
     */
    private final AreaCientificaService areaCientificaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<BibliotecarioDTO>> findAll() {
        try {
            var bibliotecarios = bibliotecarioService.findAll();
            var bibliotecariosDTO = bibliotecarios.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(bibliotecariosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/bibliotecario/{id}")
    public ResponseEntity<BibliotecarioDTO> findById(@PathVariable Long id) {
        try {
            var bibliotecario = bibliotecarioService.findById(id);
            return bibliotecario.map(b -> ok(convertToDTO(b)))
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
    public ResponseEntity<BibliotecarioDTO> findByUsername(@PathVariable String username) {
        try {
            var bibliotecario = bibliotecarioService.findBibliotecarioPorUsername(username);
            return bibliotecario.map(b -> ok(convertToDTO(b)))
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
    public ResponseEntity<BibliotecarioDTO> findByContacto(@PathVariable String contacto) {
        try {
            var bibliotecario = bibliotecarioService.findBibliotecarioPorContacto(contacto);
            return bibliotecario.map(b -> ok(convertToDTO(b)))
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
    public ResponseEntity<BibliotecarioDTO> findByEmail(@PathVariable String email) {
        try {
            var bibliotecario = bibliotecarioService.findBibliotecarioPorEmail(email);
            return bibliotecario.map(b -> ok(convertToDTO(b)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param bibliotecarioDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody BibliotecarioDTO bibliotecarioDTO) {
        try {
            var newBibliotecario = bibliotecarioService.create(convertToEntity(bibliotecarioDTO));
            var newBibliotecarioDTO = convertToDTO(newBibliotecario);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newBibliotecarioDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param bibliotecarioDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody BibliotecarioDTO bibliotecarioDTO, @PathVariable Long id) {
        try {
            bibliotecarioService.update(convertToEntity(bibliotecarioDTO));
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
            bibliotecarioService.delete(id);
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
     * @param bibliotecario
     * @return
     */
    private BibliotecarioDTO convertToDTO(Bibliotecario bibliotecario) {

        var bibliotecarioDTO = new BibliotecarioDTO();

        bibliotecarioDTO.setId(bibliotecario.getUtilizador().getId());
        bibliotecarioDTO.setNome(bibliotecario.getUtilizador().getNome());
        bibliotecarioDTO.setEmail(bibliotecario.getUtilizador().getEmail());
        bibliotecarioDTO.setSexo(bibliotecario.getUtilizador().getSexo());
        bibliotecarioDTO.setContacto(bibliotecario.getUtilizador().getContacto());
        bibliotecarioDTO.setUsername(bibliotecario.getUtilizador().getUsername());
        bibliotecarioDTO.setAreaCientifica(bibliotecario.getUtilizador().getAreaCientifica().getDescricao());
        bibliotecarioDTO.setDepartamento(bibliotecario.getUtilizador().getDepartamento().getDescricao());
        bibliotecarioDTO.setFaculdade(bibliotecario.getFaculdade().getDescricao());

        return bibliotecarioDTO;
    }

    /**
     *
     * @param bibliotecarioDTO
     * @return
     */
    private Bibliotecario convertToEntity(BibliotecarioDTO bibliotecarioDTO) {

        var bibliotecario = new Bibliotecario();
        bibliotecario.setId(bibliotecarioDTO.getId());
        bibliotecario.getUtilizador().setNome(bibliotecarioDTO.getNome());
        bibliotecario.getUtilizador().setEmail(bibliotecarioDTO.getEmail());
        bibliotecario.getUtilizador().setSexo(bibliotecarioDTO.getSexo());
        bibliotecario.getUtilizador().setContacto(bibliotecarioDTO.getContacto());
        bibliotecario.getUtilizador().setUsername(bibliotecarioDTO.getUsername());
        bibliotecario.getUtilizador()
                .setAreaCientifica(areaCientificaService.findByDescricao((bibliotecarioDTO.getAreaCientifica())));
        bibliotecario.getUtilizador()
                .setDepartamento(departamentoService.findByDescricao(bibliotecarioDTO.getDepartamento()));
        bibliotecario.setFaculdade(faculdadeService.findByDescricao(bibliotecarioDTO.getFaculdade()));

        return bibliotecario;
    }

}
