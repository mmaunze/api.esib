package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.EstudanteRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;

    // CRUD methods

    @Transactional
    public Estudante create(Estudante estudante) {
        // Verifique se o utilizador associado existe
        Utilizador utilizador = estudante.getUtilizador();
        if (utilizador == null) {
            throw new RuntimeException("Utilizador associado ao estudante não informado");
        }

        // Verifique se o utilizador já está cadastrado como estudante
        var estudanteExistente = findById(estudante.getId());
        if (estudanteExistente != null) {
            throw new RuntimeException("Utilizador já cadastrado como estudante");
        }

        // Salve o estudante
        return estudanteRepository.save(estudante);
    }

    public Optional<Estudante> findById(Long id) {
        return estudanteRepository.findById(id);
    }

    public List<Estudante> findAll() {
        return estudanteRepository.findAll();
    }

    public List<Estudante> findEstudantesPorNivel(int nivel) {
        return estudanteRepository.findByNivel(nivel);
    }

    @Transactional
    public Estudante update(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    @Transactional
    public void delete(Long id) {
        estudanteRepository.deleteById(id);
    }

    // Method related to relationship

    public Utilizador findUtilizadorPorEstudante(Estudante estudante) {
        return estudante.getUtilizador();
    }

    public Optional<Estudante> findByUsername(String username) {
        return estudanteRepository.findByUsername(username);
    }

    public Optional<Estudante> findByContacto(String contacto) {

        return estudanteRepository.findByContacto(contacto);
    }

    public Optional<Estudante> findByEmail(String email) {
        return estudanteRepository.findByEmail(email);
    }

    public Optional<Estudante> findByCurso(String curso) {
        return estudanteRepository.findByCurso(curso);
    }

}
