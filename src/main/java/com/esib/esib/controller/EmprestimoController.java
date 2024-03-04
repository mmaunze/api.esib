package com.esib.esib.controller;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.dto.EmprestimoDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.EmprestimoService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.UtilizadorService;
import java.net.URI;
import java.util.ArrayList;
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
@RequestMapping("/emprestimos")
@RequiredArgsConstructor

public class EmprestimoController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(EmprestimoController.class.getName());

    /**
     *
     */
    private final EmprestimoService emprestimoService;

    /**
     *
     */
    private final UtilizadorService utilizadorService;

    /**
     *
     */
    private final BibliotecarioService bibliotecarioService;

    /**
     *
     */
    private final EstadoService estadoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<EmprestimoDTO>> findAll() {
        try {
            var emprestimos = emprestimoService.findAll();
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/emprestimo/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id) {
        try {
            var emprestimo = emprestimoService.findById(id);
            return emprestimo.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<List<EmprestimoDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var emprestimos = emprestimoService.findByTitulo(titulo);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
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
    public ResponseEntity<List<EmprestimoDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var emprestimos = emprestimoService.findByIdioma(idioma);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
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
    public ResponseEntity<List<EmprestimoDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var emprestimos = emprestimoService.findByAreaCientifica(areacientifica);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param estado
     * @return
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EmprestimoDTO>> findByEstado(@PathVariable String estado) {
        try {
            var emprestimos = emprestimoService.findByEstado(estado);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
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
    public ResponseEntity<List<EmprestimoDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            var emprestimos = emprestimoService.findByUtilizador(utilizador);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
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
    public ResponseEntity<List<EmprestimoDTO>> findByBibliotecario(@PathVariable Long bibliotecario) {
        try {
            var emprestimos = emprestimoService.findByBibliotecario(bibliotecario);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
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
    public ResponseEntity<List<EmprestimoDTO>> findByObra(@PathVariable Long obra) {
        try {
            var emprestimos = emprestimoService.findByObra(obra);
            var emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(emprestimosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param emprestimoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            var newEmprestimo = emprestimoService.create(convertToEntity(emprestimoDTO));
            var newEmprestimoDTO = convertToDTO(newEmprestimo);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newEmprestimoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param emprestimoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody EmprestimoDTO emprestimoDTO, @PathVariable Long id) {
        try {
            emprestimoService.update(convertToEntity(emprestimoDTO));
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
            emprestimoService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param emprestimo
     * @return
     */
    private EmprestimoDTO convertToDTO(Emprestimo emprestimo) {

        var listaAutores = new ArrayList<String>();

        listaAutores.add(emprestimo.getObra().getAutor1());

        if (!emprestimo.getObra().getAutor2().isEmpty()) {
            listaAutores.add(emprestimo.getObra().getAutor1());
        }

        if (!emprestimo.getObra().getAutor3().isEmpty()) {
            listaAutores.add(emprestimo.getObra().getAutor1());
        }

        var emprestimoDTO = new EmprestimoDTO();

        emprestimoDTO.setId(emprestimo.getId());

        emprestimoDTO.setUtilizador(emprestimo.getUtilizador().getId());
        emprestimoDTO.setUtlizadorNome(emprestimo.getUtilizador().getNome());

        emprestimoDTO.setBibliotecario(emprestimo.getBibliotecario().getUtilizador().getId());
        emprestimoDTO.setBibliotecarioNome(emprestimo.getBibliotecario().getUtilizador().getNome());

        emprestimoDTO.setTituloObra(emprestimo.getObra().getTitulo());
        emprestimoDTO.setAtraso(emprestimo.getAtraso());
        emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
        emprestimoDTO.setDataParaDevolucao(emprestimo.getDataParaDevolucao());
        emprestimoDTO.setEstado(emprestimo.getEstado().getDescricao());

        return emprestimoDTO;
    }

    /**
     *
     * @param emprestimoDTO
     * @return
     */
    private Emprestimo convertToEntity(EmprestimoDTO emprestimoDTO) {
        var emprestimo = new Emprestimo();
        emprestimo.setId(emprestimoDTO.getId());

        emprestimo.setId(emprestimoDTO.getId());
        emprestimo.setUtilizador(utilizadorService.findById(emprestimoDTO.getUtilizador()).get());
        emprestimo.setBibliotecario(bibliotecarioService.findById(emprestimoDTO.getBibliotecario()).get());
        emprestimo.setAtraso(emprestimoDTO.getAtraso());
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataParaDevolucao(emprestimoDTO.getDataParaDevolucao());
        emprestimo.setEstado(estadoService.findByDescricao(emprestimoDTO.getEstado()));

        return emprestimo;
    }

}
