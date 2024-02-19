package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    // CRUD methods

    @Transactional
    public Estudante criarEstudante(Estudante estudante) {
        // Verifique se o utilizador associado existe
        Utilizador utilizador = estudante.getUtilizador();
        if (utilizador == null) {
            throw new RuntimeException("Utilizador associado ao estudante não informado");
        }

        // Verifique se o utilizador já está cadastrado como estudante
        var estudanteExistente = buscarEstudantePorId(estudante.getIdUtilizador());
        if (estudanteExistente != null) {
            throw new RuntimeException("Utilizador já cadastrado como estudante");
        }

        // Salve o estudante
        return estudanteRepository.save(estudante);
    }

    public Optional<Estudante> buscarEstudantePorId(Long id) {
        return estudanteRepository.findById(id);
    }

    public List<Estudante> buscarTodosEstudantes() {
        return estudanteRepository.findAll();
    }

    public List<Estudante> buscarEstudantesPorCurso(String curso) {
        return estudanteRepository.findByCurso(curso);
    }

    public List<Estudante> buscarEstudantesPorNivel(int nivel) {
        return estudanteRepository.findByNivel(nivel);
    }

    @Transactional
    public Estudante atualizarEstudante(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    @Transactional
    public void excluirEstudante(Long id) {
        estudanteRepository.deleteById(id);
    }

    // Method related to relationship

    public Utilizador buscarUtilizadorPorEstudante(Estudante estudante) {
        return estudante.getUtilizador();
    }

}
