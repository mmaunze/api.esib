package com.esib.esib.service;

import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.repository.CursoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // CRUD methods

    @Transactional
    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> buscarTodosCursos() {
        return cursoRepository.findAll();
    }

    public List<Curso> buscarCursosPorFaculdade(String faculdade) {
        return cursoRepository.findByFaculdade(faculdade);
    }

    @Transactional
    public Curso atualizarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public void excluirCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Estudante> buscarEstudantesPorCurso(Curso curso) {
        return curso.getEstudanteList();
    }

    public List<Monografia> buscarMonografiasPorCurso(Curso curso) {
        return curso.getMonografiaList();
    }

}
