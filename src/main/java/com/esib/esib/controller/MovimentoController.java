package com.esib.esib.controller;

import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esib.esib.model.Movimento;
import com.esib.esib.model.dto.MovimentoDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.MovimentoService;
import com.esib.esib.service.ObraService;
import com.esib.esib.service.TipoMovimentoService;
import com.esib.esib.service.UtilizadorService;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author Meldo Maunze
 */
@RestController
@RequestMapping("/movimentos")
@RequiredArgsConstructor

public class MovimentoController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(MovimentoController.class.getName());

    /**
     *
     */
    private final MovimentoService movimentoService;

    /**
     *
     */
    private final TipoMovimentoService tipoMovimentoService;

    /**
     *
     */
    private final BibliotecarioService bibliotecarioService;

    /**
     *
     */
    private final UtilizadorService utilizadorService;

    /**
     *
     */
    private final ObraService obraService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<MovimentoDTO>> findAll() {
        try {
            var movimentos = movimentoService.findAll();
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/movimento/{id}")
    public ResponseEntity<MovimentoDTO> findById(@PathVariable Long id) {
        try {
            var movimento = movimentoService.findById(id);
            return movimento.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<List<MovimentoDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var movimentos = movimentoService.findByTitulo(titulo);
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param utilizador
     * @return
     */
    @GetMapping("/utilizador/{utilizador}")
    public ResponseEntity<List<MovimentoDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            var movimentos = movimentoService.findByUtilizador(utilizador);
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    @GetMapping("/bibliotecario/{bibliotecario}")
    public ResponseEntity<List<MovimentoDTO>> findByBibliotecario(@PathVariable Long bibliotecario) {
        try {
            var movimentos = movimentoService.findByBibliotecario(bibliotecario);
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param obra
     * @return
     */
    @GetMapping("/obra/{obra}")
    public ResponseEntity<List<MovimentoDTO>> findByObra(@PathVariable Long obra) {
        try {
            var movimentos = movimentoService.findByObra(obra);
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
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
    public ResponseEntity<List<MovimentoDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var movimentos = movimentoService.findByIdioma(idioma);
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
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
    public ResponseEntity<List<MovimentoDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var movimentos = movimentoService.findByAreaCientifica(areacientifica);
            var movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(movimentosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param movimentoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MovimentoDTO movimentoDTO) {
        try {
            var newMovimento = movimentoService.create(convertToEntity(movimentoDTO));
            var newMovimentoDTO = convertToDTO(newMovimento);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newMovimentoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param movimentoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody MovimentoDTO movimentoDTO, @PathVariable Long id) {
        try {
            movimentoService.update(convertToEntity(movimentoDTO));
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
            movimentoService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param movimento
     * @return
     */
    private MovimentoDTO convertToDTO(Movimento movimento) {

        var movimentoDTO = new MovimentoDTO();

        movimentoDTO.setId(movimento.getId());

        movimentoDTO.setUtilizador(movimento.getUtilizador().getId());
        movimentoDTO.setNomeUtilizador(movimento.getUtilizador().getNome());

        movimentoDTO.setBibliotecario(movimento.getBibliotecario().getUtilizador().getId());
        movimentoDTO.setNomeBibliotecario(movimento.getBibliotecario().getUtilizador().getNome());

        movimentoDTO.setObra(movimento.getObra().getId());
        movimentoDTO.setTipoMovimento(movimento.getTipoMovimento().getDescricao());

        movimentoDTO.setDataMovimento(movimento.getDataMovimento());
        movimentoDTO.setObservacao(movimento.getObservacao().toUpperCase());
        return movimentoDTO;
    }

    /**
     *
     * @param movimentoDTO
     * @return
     */
    private Movimento convertToEntity(MovimentoDTO movimentoDTO) {
        var movimento = new Movimento();

        movimento.setId(movimento.getId());
        movimento.setUtilizador(utilizadorService.findById(movimentoDTO.getUtilizador()).get());
        movimento.setBibliotecario(bibliotecarioService.findById(movimentoDTO.getBibliotecario()).get());
        movimento.setObra(obraService.findById(movimentoDTO.getObra()).get());
        movimento.setTipoMovimento(tipoMovimentoService.findByDescricao(movimentoDTO.getTipoMovimento()));
        movimento.setDataMovimento(movimentoDTO.getDataMovimento());
        movimento.setObservacao(movimentoDTO.getObservacao().toUpperCase());

        return movimento;
    }

}
