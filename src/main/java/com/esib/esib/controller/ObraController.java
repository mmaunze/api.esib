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

import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.dto.ObraDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.ObraService;
import com.esib.esib.service.TipoObraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor

public class ObraController {

    private final ObraService obraService;
    private final IdiomaService idiomaService;
    private final TipoObraService tipoObraService;
    private final AreaCientificaService areaCientificaService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<ObraDTO>> findAll() {
        try {
            List<Obra> obras = obraService.findAll();
            List<ObraDTO> obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(obrasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obra/{id}")
    public ResponseEntity<ObraDTO> findById(@PathVariable Long id) {
        try {
            Optional<Obra> obra = obraService.findById(id);
            return obra.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ObraDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Obra> obras = obraService.findByTitulo(titulo);
            List<ObraDTO> obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(obrasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<ObraDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Obra> obras = obraService.findByIdioma(idioma);
            List<ObraDTO> obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(obrasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<ObraDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Obra> obras = obraService.findByAreaCientifica(areacientifica);
            List<ObraDTO> obrasDTO = obras.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(obrasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody ObraDTO obraDTO) {
        try {
            Obra newObra = obraService.create(convertToEntity(obraDTO));
            ObraDTO newObraDTO = convertToDTO(newObra);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newObraDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody ObraDTO obraDTO, @PathVariable Long id) {
        try {
            obraService.update(convertToEntity(obraDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            obraService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ObraDTO convertToDTO(Obra obra) {
        ObraDTO obraDTO = new ObraDTO();
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

        return obraDTO;
    }

    private Obra convertToEntity(ObraDTO obraDTO) {

        Obra obra = new Obra();
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

        return obra;
    }
}
