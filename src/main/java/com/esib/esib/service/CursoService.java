package com.esib.esib.service;

import com.esib.esib.modelo.Curso;
import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.Monografia;
import com.esib.esib.repository.CursoRepository;
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
public class CursoService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(CursoService.class.getName());

    /**
     *
     */
    private final CursoRepository cursoRepository;

    // CRUD methods
    /**
     *
     * @param curso
     * @return
     */
    @Transactional
    public Curso create(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    /**
     *
     * @param faculdade
     * @return
     */
    public List<Curso> findCursosPorFaculdade(String faculdade) {
        return cursoRepository.findByFaculdade(faculdade);
    }

    /**
     *
     * @param curso
     * @return
     */
    @Transactional
    public Curso update(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    // Methods related to relationships
    /**
     *
     * @param curso
     * @return
     */
    public List<Estudante> findEstudantesPorCurso(Curso curso) {
        return curso.getEstudanteList();
    }

    /**
     *
     * @param curso
     * @return
     */
    public List<Monografia> findMonografiasPorCurso(Curso curso) {
        return curso.getMonografiaList();
    }

    /**
     *
     * @param curso
     * @return
     */
    public Curso findByDescricao(String curso) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
