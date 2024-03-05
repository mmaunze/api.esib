package com.esib.esib.controller;

import com.esib.esib.model.Obra;
import com.esib.esib.model.dto.ObraDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.ObraService;
import com.esib.esib.service.TipoObraService;
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
@RequestMapping("/obras")
@RequiredArgsConstructor

public class ObraController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(ObraController.class.getName());

    /**
     *
     */
    private final ObraService obraService;

    /**
     *
     */
    private final IdiomaService idiomaService;

    /**
     *
     */
    private final TipoObraService tipoObraService;

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
    public ResponseEntity<List<ObraDTO>> findAll() {
        try {
            var obras = obraService.findAll();
            var obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(obrasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/obra/{id}")
    public ResponseEntity<ObraDTO> findById(@PathVariable Long id) {
        try {
            var obra = obraService.findById(id);
            return obra.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<List<ObraDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var obras = obraService.findByTitulo(titulo);
            var obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(obrasDTO, OK);
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
    public ResponseEntity<List<ObraDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var obras = obraService.findByIdioma(idioma);
            var obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(obrasDTO, OK);
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
    public ResponseEntity<List<ObraDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var obras = obraService.findByAreaCientifica(areacientifica);
            var obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(obrasDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param obraDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody ObraDTO obraDTO) {
        try {
            var newObra = obraService.create(convertToEntity(obraDTO));
            var newObraDTO = convertToDTO(newObra);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newObraDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param obraDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody ObraDTO obraDTO, @PathVariable Long id) {
        try {
            obraService.update(convertToEntity(obraDTO));
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
            obraService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param obra
     * @return
     */
    private ObraDTO convertToDTO(Obra obra) {
        var obraDTO = new ObraDTO();
        obraDTO.setId(obra.getId());
        obraDTO.setTitulo(obra.getTitulo());
        obraDTO.setAutores(obra.getAutor1() + " " + obra.getAutor2() + " " + obra.getAutor3());
        obraDTO.setNrPaginas(obra.getNrPaginas());
        obraDTO.setLocalPublicacao(obra.getLocalPublicacao());
        obraDTO.setAnoPublicacao(obra.getAnoPublicacao());
        obraDTO.setTipoObra(obra.getTipoObra().getDescricao());
        obraDTO.setFotografia(obra.getFotografia());
        obraDTO.setIdioma(obra.getIdioma().getDescricao());
        obraDTO.setEstado(obra.getEstado().getDescricao());
        obraDTO.setAreaCientifica(obra.getAreaCientifica().getDescricao());
        obraDTO.setLocalizacao(obra.getLocalizacao());
        obraDTO.setReferencia(obra.getReferencia());

        return obraDTO;
    }

    /**
     *
     * @param obraDTO
     * @return
     */
    private Obra convertToEntity(ObraDTO obraDTO) {

        var obra = new Obra();
        obra.setId(obraDTO.getId());
        obra.setTitulo(obraDTO.getTitulo());
        obra.setAutor1(obraDTO.getAutores());
        obra.setNrPaginas(obraDTO.getNrPaginas());
        obra.setLocalPublicacao(obraDTO.getLocalPublicacao());
        obra.setAnoPublicacao(obraDTO.getAnoPublicacao());
        obra.setTipoObra(tipoObraService.findTipoObraPorDescricao(obraDTO.getTipoObra()));
        obra.setFotografia(obraDTO.getFotografia());
        obra.setIdioma(idiomaService.findByDescricao(obraDTO.getIdioma()));
        obra.setEstado(estadoService.findByDescricao(obraDTO.getEstado()));
        obra.setAreaCientifica(areaCientificaService.findByDescricao(obraDTO.getAreaCientifica()));
        obra.setLocalizacao(obraDTO.getLocalizacao());
        obra.setReferencia(obraDTO.getReferencia());

        return obra;
    }

}
