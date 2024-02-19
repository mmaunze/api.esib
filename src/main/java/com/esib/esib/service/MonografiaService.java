package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.MonografiaRepository;

@Service
public class MonografiaService {

    @Autowired
    private MonografiaRepository monografiaRepository;

    // CRUD methods

    @Transactional
    public Monografia criarMonografia(Monografia monografia) {
        // Verifique se a obra associada existe
        Obra obra = monografia.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada à monografia não informada");
        }

        // Verifique se o ISBN da obra já está associado a outra monografia
        if (obra.getMonografia() != null) {
            throw new RuntimeException("Obra já está associada a outra monografia");
        }

        // Associe a monografia à obra
        obra.setMonografia(monografia);

        return monografiaRepository.save(monografia);
    }

    public Optional<Monografia> buscarMonografiaPorId(Long id) {
        return monografiaRepository.findById(id);
    }

    public List<Monografia> buscarTodasMonografias() {
        return monografiaRepository.findAll();
    }

    /*
     * public List<Monografia> buscarMonografiasPorCurso(String curso) {
     * return monografiaRepository.findByCurso(curso);
     * }
     * 
     * public List<Monografia> buscarMonografiasPorFaculdade(String faculdade) {
     * return monografiaRepository.findByFaculdade(faculdade);
     * }
     */
    @Transactional
    public Monografia atualizarMonografia(Monografia monografia) {
        return monografiaRepository.save(monografia);
    }

    @Transactional
    public void excluirMonografia(Long id) {
        var monografia = monografiaRepository.findById(id);

        /*
         * // Verifique se a monografia possui empréstimos antes de excluir
         * Obra obra = monografia.getIdObra();
         * if (obra != null && obra.getEmprestimoList() != null &&
         * !obra.getEmprestimoList().isEmpty()) {
         * throw new
         * RuntimeException("Monografia possui empréstimos associados e não pode ser excluída"
         * );
         * }
         */
        monografiaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Obra buscarObraPorMonografia(Monografia monografia) {
        return monografia.getObra();
    }

    public Curso buscarCursoPorMonografia(Monografia monografia) {
        return monografia.getCurso();
    }

    public Faculdade buscarFaculdadePorMonografia(Monografia monografia) {
        return monografia.getFaculdade();
    }

}
