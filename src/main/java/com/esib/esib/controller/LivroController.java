package com.esib.esib.controller;

import com.esib.esib.modelo.Livro;
import com.esib.esib.modelo.dto.LivroDTO;
import com.esib.esib.service.AreaCientificaService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.IdiomaService;
import com.esib.esib.service.LivroService;
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
@RequestMapping("/obras/livros")
@RequiredArgsConstructor

public class LivroController {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(LivroController.class.getName());

    /**
     *
     */
    private final LivroService livroService;

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
    public ResponseEntity<List<LivroDTO>> findAll() {
        try {
            var livros = livroService.findAll();
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/livro/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        try {
            var livros = livroService.findById(id);
            return livros.map(r -> ok(convertToDTO(r)))
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
    public ResponseEntity<List<LivroDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var livros = livroService.findByTitulo(titulo);
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
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
    public ResponseEntity<List<LivroDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var livros = livroService.findByIdioma(idioma);
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
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
    public ResponseEntity<List<LivroDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var livros = livroService.findByAreaCientifica(areacientifica);
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
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
    public ResponseEntity<List<LivroDTO>> findByVolume(@PathVariable Long volume) {
        try {
            var livros = livroService.findByVolume(volume);
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
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
    public ResponseEntity<List<LivroDTO>> findByEditora(@PathVariable String editora) {
        try {
            var livros = livroService.findByEditora(editora);
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param edicao
     * @return
     */
    @GetMapping("/edicao/{edicao}")
    public ResponseEntity<List<LivroDTO>> findByNumero(@PathVariable Integer edicao) {
        try {
            var livros = livroService.findByEdicao(edicao);
            var livrosDTO = livros.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(livrosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param livroDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody LivroDTO livroDTO) {
        try {
            var newLivro = livroService.create(convertToEntity(livroDTO));
            var newLivroDTO = convertToDTO(newLivro);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newLivroDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param livroDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody LivroDTO livroDTO, @PathVariable Long id) {
        try {
            livroService.update(convertToEntity(livroDTO));
            return noContent().build();
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
            livroService.delete(id);
            return noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param livro
     * @return
     */
    private LivroDTO convertToDTO(Livro livro) {
        var livroDTO = new LivroDTO();

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

    /**
     *
     * @param livroDTO
     * @return
     */
    private Livro convertToEntity(LivroDTO livroDTO) {

        var livro = new Livro();
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
