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

import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.dto.DevolucaoDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.DevolucaoService;
import com.esib.esib.service.EmprestimoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emprestimos/devolucao")
@RequiredArgsConstructor

public class DevolucaoController {

    private final DevolucaoService devolucaoService;
    private final EmprestimoService emprestimoService;
    private final BibliotecarioService bibliotecarioService;

    @GetMapping()
    public ResponseEntity<List<DevolucaoDTO>> findAll() {
        try {
            List<Devolucao> devolucoes = devolucaoService.findAll();
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/devolucao/{id}")
    public ResponseEntity<DevolucaoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Devolucao> devolucao = devolucaoService.findById(id);
            return devolucao.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<DevolucaoDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Devolucao> devolucoes = devolucaoService.findByTitulo(titulo);
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/utilizador/{utilizador}")
    public ResponseEntity<List<DevolucaoDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            List<Devolucao> devolucoes = devolucaoService.findByUtilizador(utilizador);
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bibliotecario/{bibliotecario}")
    public ResponseEntity<List<DevolucaoDTO>> findByBibliotecario(@PathVariable Long bibliotecario) {
        try {
            List<Devolucao> devolucoes = devolucaoService.findByBibliotecario(bibliotecario);
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obra/{obra}")
    public ResponseEntity<List<DevolucaoDTO>> findByObra(@PathVariable Long obra) {
        try {
            List<Devolucao> devolucoes = devolucaoService.findByObra(obra);
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<DevolucaoDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Devolucao> devolucoes = devolucaoService.findByIdioma(idioma);
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<DevolucaoDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Devolucao> devolucoes = devolucaoService.findByAreaCientifica(areacientifica);
            List<DevolucaoDTO> devolucoesDTO = devolucoes.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(devolucoesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody DevolucaoDTO devolucaoDTO) {
        try {
            Devolucao newdevolucao = devolucaoService.create(convertToEntity(devolucaoDTO));
            DevolucaoDTO newDevolucaoDTO = convertToDTO(newdevolucao);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newDevolucaoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody DevolucaoDTO DevolucaoDTO, @PathVariable Long id) {
        try {
            devolucaoService.update(convertToEntity(DevolucaoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            devolucaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private DevolucaoDTO convertToDTO(Devolucao devolucao) {

        DevolucaoDTO devolucaoDTO = new DevolucaoDTO();

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

    private Devolucao convertToEntity(DevolucaoDTO devolucaoDTO) {
        Devolucao devolucao = new Devolucao();
        devolucao.setId(devolucaoDTO.getId());

        devolucao.setId(devolucaoDTO.getId());
        devolucao.setEmprestimo(emprestimoService.findById(devolucaoDTO.getEmprestimo()).get());
        devolucao.setBibliotecario(bibliotecarioService.findById(devolucaoDTO.getBibliotecario()).get());
        devolucao.setAtraso(devolucaoDTO.getAtraso());
        devolucao.setDataDevolucao(devolucaoDTO.getDataDevolucao());

        return devolucao;
    }
}
