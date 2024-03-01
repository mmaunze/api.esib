package com.esib.esib.controller;

import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.dto.DevolucaoDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.DevolucaoService;
import com.esib.esib.service.EmprestimoService;
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
@RequestMapping("/emprestimos/devolucao")
@RequiredArgsConstructor

public class DevolucaoController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(DevolucaoController.class.getName());

    /**
     *
     */
    private final DevolucaoService devolucaoService;

    /**
     *
     */
    private final EmprestimoService emprestimoService;

    /**
     *
     */
    private final BibliotecarioService bibliotecarioService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<DevolucaoDTO>> findAll() {
        try {
            var devolucoes = devolucaoService.findAll();
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/devolucao/{id}")
    public ResponseEntity<DevolucaoDTO> findById(@PathVariable Long id) {
        try {
            var devolucao = devolucaoService.findById(id);
            return devolucao.map(u -> ok(convertToDTO(u)))
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
    public ResponseEntity<List<DevolucaoDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            var devolucoes = devolucaoService.findByTitulo(titulo);
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
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
    public ResponseEntity<List<DevolucaoDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            var devolucoes = devolucaoService.findByUtilizador(utilizador);
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
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
    public ResponseEntity<List<DevolucaoDTO>> findByBibliotecario(@PathVariable Long bibliotecario) {
        try {
            var devolucoes = devolucaoService.findByBibliotecario(bibliotecario);
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
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
    public ResponseEntity<List<DevolucaoDTO>> findByObra(@PathVariable Long obra) {
        try {
            var devolucoes = devolucaoService.findByObra(obra);
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
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
    public ResponseEntity<List<DevolucaoDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            var devolucoes = devolucaoService.findByIdioma(idioma);
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
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
    public ResponseEntity<List<DevolucaoDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            var devolucoes = devolucaoService.findByAreaCientifica(areacientifica);
            var devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(devolucoesDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param devolucaoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody DevolucaoDTO devolucaoDTO) {
        try {
            var newdevolucao = devolucaoService.create(convertToEntity(devolucaoDTO));
            var newDevolucaoDTO = convertToDTO(newdevolucao);

            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newDevolucaoDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param DevolucaoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody DevolucaoDTO DevolucaoDTO, @PathVariable Long id) {
        try {
            devolucaoService.update(convertToEntity(DevolucaoDTO));
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
            devolucaoService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param devolucao
     * @return
     */
    private DevolucaoDTO convertToDTO(Devolucao devolucao) {

        var devolucaoDTO = new DevolucaoDTO();

        devolucaoDTO.setId(devolucao.getId());
        devolucaoDTO.setEmprestimo(devolucao.getEmprestimo().getId());

        devolucaoDTO.setUtilizador(devolucao.getEmprestimo().getUtilizador().getId());
        devolucaoDTO.setUtlizadorNome(devolucao.getEmprestimo().getUtilizador().getNome());

        devolucaoDTO.setBibliotecario(devolucao.getBibliotecario().getUtilizador().getId());
        devolucaoDTO.setBibliotecarioNome(devolucao.getBibliotecario().getUtilizador().getNome());

        devolucaoDTO.setTituloObra(devolucao.getEmprestimo().getObra().getTitulo());
        devolucaoDTO.setAtraso(devolucao.getAtraso());
        devolucaoDTO.setDataDevolucao(devolucao.getDataDevolucao());

        return devolucaoDTO;
    }

    /**
     *
     * @param devolucaoDTO
     * @return
     */
    private Devolucao convertToEntity(DevolucaoDTO devolucaoDTO) {
        var devolucao = new Devolucao();
        devolucao.setId(devolucaoDTO.getId());

        devolucao.setId(devolucaoDTO.getId());
        devolucao.setEmprestimo(emprestimoService.findById(devolucaoDTO.getEmprestimo()).get());
        devolucao.setBibliotecario(bibliotecarioService.findById(devolucaoDTO.getBibliotecario()).get());
        devolucao.setAtraso(devolucaoDTO.getAtraso());
        devolucao.setDataDevolucao(devolucaoDTO.getDataDevolucao());

        return devolucao;
    }

}
