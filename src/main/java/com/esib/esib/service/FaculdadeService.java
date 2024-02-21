package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.repository.FaculdadeRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class FaculdadeService {

    private final FaculdadeRepository faculdadeRepository;

    // CRUD methods

    @Transactional
    public Faculdade create(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    public Optional<Faculdade> findById(Long id) {
        return faculdadeRepository.findById(id);
    }

    public List<Faculdade> findAll() {
        return faculdadeRepository.findAll();
    }

    @Transactional
    public Faculdade update(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    @Transactional
    public void delete(Long id) {
        var faculdade = faculdadeRepository.getById(id);
        // Verifique se a faculdade possui entidades relacionadas antes de excluir
        List<Curso> cursos = faculdade.getCursoList();
        List<Monografia> monografias = faculdade.getMonografiaList();
        List<Bibliotecario> bibliotecarios = faculdade.getBibliotecarioList();

        if (!cursos.isEmpty() || !monografias.isEmpty() || !bibliotecarios.isEmpty()) {
            throw new RuntimeException("Faculdade possui entidades relacionadas e não pode ser excluída");
        }

        faculdadeRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Curso> findCursosPorFaculdade(Faculdade faculdade) {
        return faculdade.getCursoList();
    }

    public List<Monografia> findMonografiasPorFaculdade(Faculdade faculdade) {
        return faculdade.getMonografiaList();
    }

    public List<Bibliotecario> findBibliotecariosPorFaculdade(Faculdade faculdade) {
        return faculdade.getBibliotecarioList();
    }

    public Faculdade findByDescricao(String faculdade) {
        return faculdadeRepository.findByDescricao(faculdade);
    }
}
