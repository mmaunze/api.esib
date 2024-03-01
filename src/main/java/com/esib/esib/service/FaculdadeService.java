package com.esib.esib.service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Faculdade;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.repository.FaculdadeRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Meldo Maunze
 */
@Service
@RequiredArgsConstructor
@Data
public class FaculdadeService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(FaculdadeService.class.getName());

    /**
     *
     */
    private final FaculdadeRepository faculdadeRepository;

    // CRUD methods
    /**
     *
     * @param faculdade
     * @return
     */
    @Transactional
    public Faculdade create(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Faculdade> findById(Long id) {
        return faculdadeRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Faculdade> findAll() {
        return faculdadeRepository.findAll();
    }

    /**
     *
     * @param faculdade
     * @return
     */
    @Transactional
    public Faculdade update(Faculdade faculdade) {
        return faculdadeRepository.save(faculdade);
    }

    /**
     *
     * @param id
     */
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
    /**
     *
     * @param faculdade
     * @return
     */
    public List<Curso> findCursosPorFaculdade(Faculdade faculdade) {
        return faculdade.getCursoList();
    }

    /**
     *
     * @param faculdade
     * @return
     */
    public List<Monografia> findMonografiasPorFaculdade(Faculdade faculdade) {
        return faculdade.getMonografiaList();
    }

    /**
     *
     * @param faculdade
     * @return
     */
    public List<Bibliotecario> findBibliotecariosPorFaculdade(Faculdade faculdade) {
        return faculdade.getBibliotecarioList();
    }

    /**
     *
     * @param faculdade
     * @return
     */
    public Faculdade findByDescricao(String faculdade) {
        return faculdadeRepository.findByDescricao(faculdade);
    }

}
