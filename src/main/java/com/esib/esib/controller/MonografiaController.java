package com.esib.esib.controller;

import com.esib.esib.modelo.Monografia;
import com.esib.esib.modelo.dto.MonografiaDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.CursoService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.FaculdadeService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.MonografiaService;
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
@RequestMapping("/monografias")
@RequiredArgsConstructor

public class MonografiaController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(MonografiaController.class.getName());

    /**
     *
     */
    private final MonografiaService monografiaService;

    /**
     *
     */
    private final FaculdadeService faculdadeService;

    /**
     *
     */
    private final CursoService cursoService;

    /**
     *
     */
    private final IdiomaService idiomaService;

    /**
     *
     */
    private final AreaCientificaService areaCientificaService;

    /**
     *
     */
    private final EstadoService estadoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<MonografiaDTO>> findAll() {
        try {
            var monografias = monografiaService.findAll();
            var monografiaDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiaDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/monografia/{id}")
    public ResponseEntity<MonografiaDTO> findById(@PathVariable Long id) {
        try {
            var monografias = monografiaService.findById(id);
            return monografias.map(u -> ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param titulo
     * @return
     */
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<MonografiaDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var monografias = monografiaService.findByTitulo(titulo);
            var monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param idioma
     * @return
     */
    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<MonografiaDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var monografias = monografiaService.findByIdioma(idioma);
            var monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiasDTO, OK);
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
    public ResponseEntity<List<MonografiaDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var monografias = monografiaService.findByAreaCientifica(areacientifica);
            var monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param faculdade
     * @return
     */
    @GetMapping("/faculdade/{faculdade}")
    public ResponseEntity<List<MonografiaDTO>> findByFaculdade(@PathVariable String faculdade) {
        try {
            var monografias = monografiaService.findByFaculdade(faculdade);
            var monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param curso
     * @return
     */
    @GetMapping("/curso/{curso}")
    public ResponseEntity<List<MonografiaDTO>> findByCurso(@PathVariable String curso) {
        try {
            var monografias = monografiaService.findByCurso(curso);
            var monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param supervisor
     * @return
     */
    @GetMapping("/supervisor/{supervisor}")
    public ResponseEntity<List<MonografiaDTO>> findBySupervisor(@PathVariable String supervisor) {
        try {
            var monografias = monografiaService.findBySupervisor(supervisor);
            var monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(monografiasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param monografiaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MonografiaDTO monografiaDTO) {
        try {
            var newMonografia = monografiaService.create(convertToEntity(monografiaDTO));
            var newMonografiaDTO = convertToDTO(newMonografia);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newMonografiaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param monografiaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody MonografiaDTO monografiaDTO, @PathVariable Long id) {
        try {
            monografiaService.update(convertToEntity(monografiaDTO));
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
            monografiaService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param monografia
     * @return
     */
    private MonografiaDTO convertToDTO(Monografia monografia) {
        var monografiaDTO = new MonografiaDTO();

        monografiaDTO.setId(monografia.getId());
        monografiaDTO.setTitulo(monografia.getObra().getTitulo());
        monografiaDTO.setAutores(monografia.getObra().getAutor1()
                + " " + monografia.getObra().getAutor2()
                + " " + monografia.getObra().getAutor3());
        monografiaDTO.setNrPaginas(monografia.getObra().getNrPaginas());
        monografiaDTO.setLocalPublicacao(monografia.getObra().getLocalPublicacao());
        monografiaDTO.setAnoPublicacao(monografia.getObra().getAnoPublicacao());
        monografiaDTO.setFotografia(monografia.getObra().getFotografia());
        monografiaDTO.setIdioma(monografia.getObra().getIdioma().getDescricao());
        monografiaDTO.setEstado(monografia.getObra().getEstado().getDescricao());
        monografiaDTO.setAreaCientifica(monografia.getObra().getAreaCientifica().getDescricao());
        monografiaDTO.setSupervisor(monografia.getSupervisor());
        monografiaDTO.setCoSupervisor(monografia.getCoSupervisor());
        monografiaDTO.setCurso(monografia.getCurso().getDescricao());
        monografiaDTO.setFaculdade(monografia.getFaculdade().getDescricao());

        return monografiaDTO;
    }

    /**
     *
     * @param monografiaDTO
     * @return
     */
    private Monografia convertToEntity(MonografiaDTO monografiaDTO) {

        var monografia = new Monografia();
        monografia.setId(monografiaDTO.getId());
        monografia.getObra().setTitulo(monografiaDTO.getTitulo());
        monografia.getObra().setAutor1(monografiaDTO.getAutores());
        monografia.getObra().setNrPaginas(monografiaDTO.getNrPaginas());
        monografia.getObra().setLocalPublicacao(monografiaDTO.getLocalPublicacao());
        monografia.getObra().setAnoPublicacao(monografiaDTO.getAnoPublicacao());
        monografia.getObra().setFotografia(monografiaDTO.getFotografia());

        //
        monografia.getObra().setIdioma(idiomaService.findByDescricao(monografiaDTO.getIdioma()));
        monografia.getObra().setEstado(estadoService.findByDescricao(monografiaDTO.getEstado()));
        monografia.getObra()
                .setAreaCientifica(areaCientificaService.findByDescricao(monografiaDTO.getAreaCientifica()));
        monografia.setSupervisor(monografiaDTO.getSupervisor());

        monografia.setCoSupervisor(
                monografiaDTO.getCoSupervisor());
        monografia.setCurso(
                cursoService.findByDescricao(monografiaDTO.getCurso()));
        monografia.setFaculdade(
                faculdadeService.findByDescricao(monografiaDTO.getFaculdade()));
        return monografia;
    }

}
