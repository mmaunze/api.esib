package com.esib.esib.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.dto.MovimentoDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.MovimentoService;
import com.esib.esib.service.ObraService;
import com.esib.esib.service.TipoMovimentoService;
import com.esib.esib.service.UtilizadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movimentos")
@RequiredArgsConstructor

public class MovimentoController {

    private final MovimentoService movimentoService;
    private final TipoMovimentoService tipoMovimentoService;
    private final BibliotecarioService bibliotecarioService;
    private final UtilizadorService utilizadorService;
    private final ObraService obraService;

    @GetMapping()
    public ResponseEntity<List<MovimentoDTO>> findAll() {
        try {
            List<Movimento> movimentos = movimentoService.findAll();
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movimento/{id}")
    public ResponseEntity<MovimentoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Movimento> movimento = movimentoService.findById(id);
            return movimento.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<MovimentoDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Movimento> movimentos = movimentoService.findByTitulo(titulo);
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/utilizador/{utilizador}")
    public ResponseEntity<List<MovimentoDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            List<Movimento> movimentos = movimentoService.findByUtilizador(utilizador);
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bibliotecario/{bibliotecario}")
    public ResponseEntity<List<MovimentoDTO>> findByBibliotecario(@PathVariable Long bibliotecario) {
        try {
            List<Movimento> movimentos = movimentoService.findByBibliotecario(bibliotecario);
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obra/{obra}")
    public ResponseEntity<List<MovimentoDTO>> findByObra(@PathVariable Long obra) {
        try {
            List<Movimento> movimentos = movimentoService.findByObra(obra);
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<MovimentoDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Movimento> movimentos = movimentoService.findByIdioma(idioma);
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<MovimentoDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Movimento> movimentos = movimentoService.findByAreaCientifica(areacientifica);
            List<MovimentoDTO> movimentosDTO = movimentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movimentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MovimentoDTO movimentoDTO) {
        try {
            Movimento newMovimento = movimentoService.create(convertToEntity(movimentoDTO));
            MovimentoDTO newMovimentoDTO = convertToDTO(newMovimento);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newMovimentoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody MovimentoDTO movimentoDTO, @PathVariable Long id) {
        try {
            movimentoService.update(convertToEntity(movimentoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            movimentoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MovimentoDTO convertToDTO(Movimento movimento) {

        MovimentoDTO movimentoDTO = new MovimentoDTO();

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

    private Movimento convertToEntity(MovimentoDTO movimentoDTO) {
        Movimento movimento = new Movimento();

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
