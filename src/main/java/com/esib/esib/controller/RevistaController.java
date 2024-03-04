package com.esib.esib.controller;

import com.esib.esib.modelo.Revista;
import com.esib.esib.modelo.dto.RevistaDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.RevistaService;
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
@RequestMapping("/obras/revistas")
@RequiredArgsConstructor

public class RevistaController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(RevistaController.class.getName());

    /**
     *
     */
    private final RevistaService revistaService;

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
    public ResponseEntity<List<RevistaDTO>> findAll() {
        try {
            var revistas = revistaService.findAll();
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/revista/{id}")
    public ResponseEntity<RevistaDTO> findById(@PathVariable Long id) {
        try {
            var revistas = revistaService.findById(id);
            return revistas.map(r -> ok(convertToDTO(r)))
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
    public ResponseEntity<List<RevistaDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var revistas = revistaService.findByTitulo(titulo);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
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
    public ResponseEntity<List<RevistaDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var revistas = revistaService.findByIdioma(idioma);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
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
    public ResponseEntity<List<RevistaDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var revistas = revistaService.findByAreaCientifica(areacientifica);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param volume
     * @return
     */
    @GetMapping("/volume/{volume}")
    public ResponseEntity<List<RevistaDTO>> findByVolume(@PathVariable Long volume) {
        try {
            var revistas = revistaService.findByVolume(volume);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param editora
     * @return
     */
    @GetMapping("/editora/{editora}")
    public ResponseEntity<List<RevistaDTO>> findByEditora(@PathVariable String editora) {
        try {
            var revistas = revistaService.findByEditora(editora);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param nome
     * @return
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RevistaDTO>> findByNome(@PathVariable String nome) {
        try {
            var revistas = revistaService.findByNome(nome);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param numero
     * @return
     */
    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<RevistaDTO>> findByNumero(@PathVariable Integer numero) {
        try {
            var revistas = revistaService.findByNumero(numero);
            var revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(revistasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param revistaDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody RevistaDTO revistaDTO) {
        try {
            var newRevista = revistaService.create(convertToEntity(revistaDTO));
            var newRevistaDTO = convertToDTO(newRevista);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newRevistaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param revistaDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody RevistaDTO revistaDTO, @PathVariable Long id) {
        try {
            revistaService.update(convertToEntity(revistaDTO));
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
            revistaService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param revista
     * @return
     */
    private RevistaDTO convertToDTO(Revista revista) {
        var revistaDTO = new RevistaDTO();

        revistaDTO.setId(revista.getId());
        revistaDTO.setTitulo(revista.getObra().getTitulo());
        revistaDTO.setAutores(revista.getObra().getAutor1()
                + " " + revista.getObra().getAutor2()
                + " " + revista.getObra().getAutor3());
        revistaDTO.setNrPaginas(revista.getObra().getNrPaginas());
        revistaDTO.setLocalPublicacao(revista.getObra().getLocalPublicacao());
        revistaDTO.setAnoPublicacao(revista.getObra().getAnoPublicacao());
        revistaDTO.setFotografia(revista.getObra().getFotografia());
        revistaDTO.setIdioma(revista.getObra().getIdioma().getDescricao());
        revistaDTO.setEstado(revista.getObra().getEstado().getDescricao());
        revistaDTO.setAreaCientifica(revista.getObra().getAreaCientifica().getDescricao());

        revistaDTO.setEditora(revista.getEditora());
        revistaDTO.setIssn(revista.getIssn());
        revistaDTO.setNome(revista.getNome());
        revistaDTO.setNumero(revista.getNumero());
        revistaDTO.setVolume(revista.getVolume());

        return revistaDTO;
    }

    /**
     *
     * @param revistaDTO
     * @return
     */
    private Revista convertToEntity(RevistaDTO revistaDTO) {

        var revista = new Revista();
        revista.setId(revistaDTO.getId());
        revista.getObra().setTitulo(revistaDTO.getTitulo());
        revista.getObra().setAutor1(revistaDTO.getAutores());
        revista.getObra().setNrPaginas(revistaDTO.getNrPaginas());
        revista.getObra().setLocalPublicacao(revistaDTO.getLocalPublicacao());
        revista.getObra().setAnoPublicacao(revistaDTO.getAnoPublicacao());
        revista.getObra().setFotografia(revistaDTO.getFotografia());

        //
        revista.getObra().setIdioma(idiomaService.findByDescricao(revistaDTO.getIdioma()));
        revista.getObra().setEstado(estadoService.findByDescricao(revistaDTO.getEstado()));
        revista.getObra()
                .setAreaCientifica(areaCientificaService.findByDescricao(revistaDTO.getAreaCientifica()));
        //
        revista.setEditora(revistaDTO.getEditora());
        revista.setIssn(revistaDTO.getIssn());
        revista.setNome(revistaDTO.getNome());
        revista.setNumero(revistaDTO.getNumero());
        revista.setVolume(revistaDTO.getVolume());

        return revista;
    }

}
