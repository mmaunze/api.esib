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

import com.esib.esib.modelo.Revista;
import com.esib.esib.modelo.dto.RevistaDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.RevistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obras/revistas")
@RequiredArgsConstructor

public class RevistaController {

    private final RevistaService revistaService;

    private final IdiomaService idiomaService;
    private final AreaCientificaService areaCientificaService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<RevistaDTO>> findAll() {
        try {
            List<Revista> revistas = revistaService.findAll();
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/revista/{id}")
    public ResponseEntity<RevistaDTO> findById(@PathVariable Long id) {
        try {
            Optional<Revista> revistas = revistaService.findById(id);
            return revistas.map(r -> ResponseEntity.ok(convertToDTO(r)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<RevistaDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Revista> revistas = revistaService.findByTitulo(titulo);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<RevistaDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Revista> revistas = revistaService.findByIdioma(idioma);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<RevistaDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Revista> revistas = revistaService.findByAreaCientifica(areacientifica);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volume/{volume}")
    public ResponseEntity<List<RevistaDTO>> findByVolume(@PathVariable Long volume) {
        try {
            List<Revista> revistas = revistaService.findByVolume(volume);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/editora/{editora}")
    public ResponseEntity<List<RevistaDTO>> findByEditora(@PathVariable String editora) {
        try {
            List<Revista> revistas = revistaService.findByEditora(editora);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RevistaDTO>> findByNome(@PathVariable String nome) {
        try {
            List<Revista> revistas = revistaService.findByNome(nome);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<RevistaDTO>> findByNumero(@PathVariable Integer numero) {
        try {
            List<Revista> revistas = revistaService.findByNumero(numero);
            List<RevistaDTO> revistasDTO = revistas.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(revistasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody RevistaDTO revistaDTO) {
        try {
            Revista newRevista = revistaService.create(convertToEntity(revistaDTO));
            RevistaDTO newRevistaDTO = convertToDTO(newRevista);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newRevistaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody RevistaDTO revistaDTO, @PathVariable Long id) {
        try {
            revistaService.update(convertToEntity(revistaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            revistaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private RevistaDTO convertToDTO(Revista revista) {
        RevistaDTO revistaDTO = new RevistaDTO();

        revistaDTO.setId(revista.getId());
        revistaDTO.setTitulo(revista.getObra().getTitulo());
        revistaDTO.setAutores(revista.getObra().getAutor1() +
                " " + revista.getObra().getAutor2() +
                " " + revista.getObra().getAutor3());
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

    private Revista convertToEntity(RevistaDTO revistaDTO) {

        Revista revista = new Revista();
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
