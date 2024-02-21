package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.repository.CursoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class CursoService {

    private final CursoRepository cursoRepository;

    // CRUD methods

    @Transactional
    public Curso create(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public List<Curso> findCursosPorFaculdade(String faculdade) {
        return cursoRepository.findByFaculdade(faculdade);
    }

    @Transactional
    public Curso update(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Estudante> findEstudantesPorCurso(Curso curso) {
        return curso.getEstudanteList();
    }

    public List<Monografia> findMonografiasPorCurso(Curso curso) {
        return curso.getMonografiaList();
    }

    public Curso findByDescricao(String curso) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
