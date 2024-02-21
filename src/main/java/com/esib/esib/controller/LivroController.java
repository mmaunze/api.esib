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

import com.esib.esib.modelo.Livro;
import com.esib.esib.modelo.dto.LivroDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.LivroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obras/livros")
@RequiredArgsConstructor

public class LivroController {

    private final LivroService livroService;
    private final IdiomaService idiomaService;
    private final AreaCientificaService areaCientificaService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<LivroDTO>> findAll() {
        try {
            List<Livro> livros = livroService.findAll();
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/livro/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        try {
            Optional<Livro> livros = livroService.findById(id);
            return livros.map(r -> ResponseEntity.ok(convertToDTO(r)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Livro> livros = livroService.findByTitulo(titulo);
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<LivroDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Livro> livros = livroService.findByIdioma(idioma);
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<LivroDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Livro> livros = livroService.findByAreaCientifica(areacientifica);
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/volume/{volume}")
    public ResponseEntity<List<LivroDTO>> findByVolume(@PathVariable Long volume) {
        try {
            List<Livro> livros = livroService.findByVolume(volume);
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/editora/{editora}")
    public ResponseEntity<List<LivroDTO>> findByEditora(@PathVariable String editora) {
        try {
            List<Livro> livros = livroService.findByEditora(editora);
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edicao/{edicao}")
    public ResponseEntity<List<LivroDTO>> findByNumero(@PathVariable Integer edicao) {
        try {
            List<Livro> livros = livroService.findByEdicao(edicao);
            List<LivroDTO> livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(livrosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody LivroDTO livroDTO) {
        try {
            Livro newLivro = livroService.create(convertToEntity(livroDTO));
            LivroDTO newLivroDTO = convertToDTO(newLivro);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newLivroDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody LivroDTO livroDTO, @PathVariable Long id) {
        try {
            livroService.update(convertToEntity(livroDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            livroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private LivroDTO convertToDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getObra().getTitulo());
        livroDTO.setAutores(livro.getObra().getAutor1() +
                " " + livro.getObra().getAutor2() +
                " " + livro.getObra().getAutor3());
        livroDTO.setNrPaginas(livro.getObra().getNrPaginas());
        livroDTO.setLocalPublicacao(livro.getObra().getLocalPublicacao());
        livroDTO.setAnoPublicacao(livro.getObra().getAnoPublicacao());
        livroDTO.setFotografia(livro.getObra().getFotografia());
        livroDTO.setIdioma(livro.getObra().getIdioma().getDescricao());
        livroDTO.setEstado(livro.getObra().getEstado().getDescricao());
        livroDTO.setAreaCientifica(livro.getObra().getAreaCientifica().getDescricao());

        livroDTO.setEditora(livro.getEditora());
        livroDTO.setIsbn(livro.getIsbn());
        livroDTO.setEdicao(livro.getEdicao());
        livroDTO.setVolume(livro.getVolume());

        return livroDTO;
    }

    private Livro convertToEntity(LivroDTO livroDTO) {

        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.getObra().setTitulo(livroDTO.getTitulo());
        livro.getObra().setAutor1(livroDTO.getAutores());
        livro.getObra().setNrPaginas(livroDTO.getNrPaginas());
        livro.getObra().setLocalPublicacao(livroDTO.getLocalPublicacao());
        livro.getObra().setAnoPublicacao(livroDTO.getAnoPublicacao());
        livro.getObra().setFotografia(livroDTO.getFotografia());

        //
        livro.getObra().setIdioma(idiomaService.findByDescricao(livroDTO.getIdioma()));
        livro.getObra().setEstado(estadoService.findByDescricao(livroDTO.getEstado()));
        livro.getObra()
                .setAreaCientifica(areaCientificaService.findByDescricao(livroDTO.getAreaCientifica()));
        //
        livro.setEditora(livroDTO.getEditora());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setEdicao(livroDTO.getEdicao());
        livro.setVolume(livroDTO.getVolume());

        return livro;
    }
}
