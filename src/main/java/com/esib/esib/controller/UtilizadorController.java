package com.esib.esib.controller;

import com.esib.esib.modelo.Utilizador;
import com.esib.esib.modelo.dto.UtilizadorDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.DepartamentoService;
import com.esib.esib.service.TipoUtilizadorService;
import com.esib.esib.service.UtilizadorService;
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
@RequestMapping("/utilizadores")
@RequiredArgsConstructor
public class UtilizadorController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(UtilizadorController.class.getName());

    /**
     *
     */
    private final UtilizadorService utilizadorService;

    /**
     *
     */
    private final DepartamentoService departamentoService;

    /**
     *
     */
    private final TipoUtilizadorService tipoUtilizadorService;

    /**
     *
     */
    private final AreaCientificaService areaCientificaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<UtilizadorDTO>> findAll() {
        try {
            var utilizadores = utilizadorService.findAll();
            var utilizadoresDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(utilizadoresDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param areacientifica
     * @return
     */
    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<UtilizadorDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var utilizadores = utilizadorService.findByAreaCientifica(areacientifica);
            var obrasDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(obrasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param departamento
     * @return
     */
    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<UtilizadorDTO>> findByDepartamento(@PathVariable String departamento) {
        try {
            var utilizadores = utilizadorService.findByDepartamento(departamento);
            var utilizadoresDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(utilizadoresDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param tipoutilizador
     * @return
     */
    @GetMapping("/tipoutilizador/{tipoutilizador}")
    public ResponseEntity<List<UtilizadorDTO>> findByTipoUtilizador(@PathVariable String tipoutilizador) {
        try {
            var utilizadores = utilizadorService.findByTipoUtilizador(tipoutilizador);
            var utilizadoresDTO = utilizadores.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(utilizadoresDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/utilizador/{id}")
    public ResponseEntity<UtilizadorDTO> findById(@PathVariable Long id) {
        try {
            var utilizador = utilizadorService.findById(id);
            return utilizador.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<UtilizadorDTO> findByUsername(@PathVariable String username) {
        try {
            var utilizador = utilizadorService.findUtilizadorPorUsername(username);
            return utilizador.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<UtilizadorDTO> findByContacto(@PathVariable String contacto) {
        try {
            var utilizador = utilizadorService.findUtilizadorPorContacto(contacto);
            return utilizador.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<UtilizadorDTO> findByEmail(@PathVariable String email) {
        try {
            var utilizador = utilizadorService.findUtilizadorPorEmail(email);
            return utilizador.map(u -> ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param utilizadorDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody UtilizadorDTO utilizadorDTO) {
        try {
            var newUtilizador = utilizadorService.create(convertToEntity(utilizadorDTO));
            var newUtilizadorDTO = convertToDTO(newUtilizador);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newUtilizadorDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param utilizadorDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody UtilizadorDTO utilizadorDTO, @PathVariable Long id) {
        try {
            utilizadorService.update(id, convertToEntity(utilizadorDTO));
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
            utilizadorService.delete(id);
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
     * @param utilizador
     * @return
     */
    private UtilizadorDTO convertToDTO(Utilizador utilizador) {
        var utilizadorDTO = new UtilizadorDTO();
        utilizadorDTO.setId(utilizador.getId());
        utilizadorDTO.setNome(utilizador.getNome());
        utilizadorDTO.setEmail(utilizador.getEmail());
        utilizadorDTO.setSexo(utilizador.getSexo());
        utilizadorDTO.setContacto(utilizador.getContacto());
        utilizadorDTO.setUsername(utilizador.getUsername());
        utilizadorDTO.setAreaCientifica(utilizador.getAreaCientifica().getDescricao());
        utilizadorDTO.setDepartamento(utilizador.getDepartamento().getSigla());
        utilizadorDTO.setTipoUtilizador(utilizador.getTipoUtilizador().getDescricao());

        return utilizadorDTO;
    }

    /**
     *
     * @param utilizadorDTO
     * @return
     */
    private Utilizador convertToEntity(UtilizadorDTO utilizadorDTO) {

        var utilizador = new Utilizador();
        utilizador.setId(utilizadorDTO.getId());
        utilizador.setNome(utilizadorDTO.getNome());
        utilizador.setEmail(utilizadorDTO.getEmail());
        utilizador.setSexo(utilizadorDTO.getSexo());
        utilizador.setContacto(utilizadorDTO.getContacto());
        utilizador.setUsername(utilizadorDTO.getUsername());
        utilizador.setAreaCientifica(areaCientificaService.findByDescricao((utilizadorDTO.getAreaCientifica())));
        utilizador.setDepartamento(departamentoService.findByDescricao(utilizadorDTO.getDepartamento()));
        utilizador.setTipoUtilizador(tipoUtilizadorService.findByDescricao(utilizadorDTO.getTipoUtilizador()));

        return utilizador;
    }

}
