package com.esib.esib.controller;

import java.net.URI;
import java.util.ArrayList;
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

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.dto.EmprestimoDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.EmprestimoService;
import com.esib.esib.service.EstadoService;
import com.esib.esib.service.UtilizadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor

public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final UtilizadorService utilizadorService;
    private final BibliotecarioService bibliotecarioService;
    private final EstadoService estadoService;

    @GetMapping()
    public ResponseEntity<List<EmprestimoDTO>> findAll() {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findAll();
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emprestimo/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Emprestimo> emprestimo = emprestimoService.findById(id);
            return emprestimo.map(u -> ResponseEntity.ok(convertToDTO(u)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<EmprestimoDTO>> findByTitulo(@PathVariable String titulo) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByTitulo(titulo);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idioma/{idioma}")
    public ResponseEntity<List<EmprestimoDTO>> findByIdioma(@PathVariable String idioma) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByIdioma(idioma);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areacientifica/{areacientifica}")
    public ResponseEntity<List<EmprestimoDTO>> findByAreaCientifica(@PathVariable String areacientifica) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByAreaCientifica(areacientifica);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EmprestimoDTO>> findByEstado(@PathVariable String estado) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByEstado(estado);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/utilizador/{utilizador}")
    public ResponseEntity<List<EmprestimoDTO>> findByUtilizador(@PathVariable Long utilizador) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByUtilizador(utilizador);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bibliotecario/{bibliotecario}")
    public ResponseEntity<List<EmprestimoDTO>> findByBibliotecario(@PathVariable Long bibliotecario) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByBibliotecario(bibliotecario);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obra/{obra}")
    public ResponseEntity<List<EmprestimoDTO>> findByObra(@PathVariable Long obra) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.findByObra(obra);
            List<EmprestimoDTO> emprestimosDTO = emprestimos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprestimosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            Emprestimo newEmprestimo = emprestimoService.create(convertToEntity(emprestimoDTO));
            EmprestimoDTO newEmprestimoDTO = convertToDTO(newEmprestimo);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newEmprestimoDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody EmprestimoDTO emprestimoDTO, @PathVariable Long id) {
        try {
            emprestimoService.update(convertToEntity(emprestimoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            emprestimoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private EmprestimoDTO convertToDTO(Emprestimo emprestimo) {

        ArrayList<String> listaAutores = new ArrayList<>();

        listaAutores.add(emprestimo.getObra().getAutor1());

        if (!emprestimo.getObra().getAutor2().isEmpty())
            listaAutores.add(emprestimo.getObra().getAutor1());

        if (!emprestimo.getObra().getAutor3().isEmpty())
            listaAutores.add(emprestimo.getObra().getAutor1());

        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();

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

    private Emprestimo convertToEntity(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();
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
