package com.esib.esib.service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.repository.FaculdadeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaculdadeService {

    @Autowired
    private FaculdadeRepository faculdadeRepository;

    // CRUD methods

    @Transactional
    public Faculdade criarFaculdade(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    public Optional<Faculdade> buscarFaculdadePorId(Long id) {
        return faculdadeRepository.findById(id);
    }

    public List<Faculdade> buscarTodasFaculdades() {
        return faculdadeRepository.findAll();
    }

    @Transactional
    public Faculdade atualizarFaculdade(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    @Transactional
    public void excluirFaculdade(Long id) {
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

    public List<Curso> buscarCursosPorFaculdade(Faculdade faculdade) {
        return faculdade.getCursoList();
    }

    public List<Monografia> buscarMonografiasPorFaculdade(Faculdade faculdade) {
        return faculdade.getMonografiaList();
    }

    public List<Bibliotecario> buscarBibliotecariosPorFaculdade(Faculdade faculdade) {
        return faculdade.getBibliotecarioList();
    }

}
