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

import com.esib.esib.modelo.Monografia;
import com.esib.esib.modelo.dto.MonografiaDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.CursoService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.FaculdadeService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.MonografiaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/monografias")
@RequiredArgsConstructor

public class MonografiaController {

    private final MonografiaService monografiaService;
    private final FaculdadeService faculdadeService;
    private final CursoService cursoService;

    private final IdiomaService idiomaService;
    private final AreaCientificaService areaCientificaService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<MonografiaDTO>> findAll() {
        try {
            List<Monografia> monografias = monografiaService.findAll();
            List<MonografiaDTO> monografiaDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monografia/{id}")
    public ResponseEntity<MonografiaDTO> findById(@PathVariable Long id) {
        try {
            Optional<Monografia> monografias = monografiaService.findById(id);
            return monografias.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<MonografiaDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Monografia> monografias = monografiaService.findByTitulo(titulo);
            List<MonografiaDTO> monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<MonografiaDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Monografia> monografias = monografiaService.findByIdioma(idioma);
            List<MonografiaDTO> monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<MonografiaDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Monografia> monografias = monografiaService.findByAreaCientifica(areacientifica);
            List<MonografiaDTO> monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/faculdade/{faculdade}")
    public ResponseEntity<List<MonografiaDTO>> findByFaculdade(@PathVariable String faculdade) {
        try {
            List<Monografia> monografias = monografiaService.findByFaculdade(faculdade);
            List<MonografiaDTO> monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/curso/{curso}")
    public ResponseEntity<List<MonografiaDTO>> findByCurso(@PathVariable String curso) {
        try {
            List<Monografia> monografias = monografiaService.findByCurso(curso);
            List<MonografiaDTO> monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/supervisor/{supervisor}")
    public ResponseEntity<List<MonografiaDTO>> findBySupervisor(@PathVariable String supervisor) {
        try {
            List<Monografia> monografias = monografiaService.findBySupervisor(supervisor);
            List<MonografiaDTO> monografiasDTO = monografias.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(monografiasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MonografiaDTO monografiaDTO) {
        try {
            Monografia newMonografia = monografiaService.create(convertToEntity(monografiaDTO));
            MonografiaDTO newMonografiaDTO = convertToDTO(newMonografia);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newMonografiaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody MonografiaDTO monografiaDTO, @PathVariable Long id) {
        try {
            monografiaService.update(convertToEntity(monografiaDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            monografiaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MonografiaDTO convertToDTO(Monografia monografia) {
        MonografiaDTO monografiaDTO = new MonografiaDTO();

        monografiaDTO.setId(monografia.getId());
        monografiaDTO.setTitulo(monografia.getObra().getTitulo());
        monografiaDTO.setAutores(monografia.getObra().getAutor1() +
                " " + monografia.getObra().getAutor2() +
                " " + monografia.getObra().getAutor3());
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

    private Monografia convertToEntity(MonografiaDTO monografiaDTO) {

        Monografia monografia = new Monografia();
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
