package com.esib.esib.service;

import com.esib.esib.modelo.Monografia;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.MonografiaRepository;
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
public class MonografiaService {

    private final MonografiaRepository monografiaRepository;

    // CRUD methods

    /**
     *
     * @param monografia
     * @return
     */

    @Transactional
    public Monografia create(Monografia monografia) {
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

    /**
     *
     * @param id
     * @return
     */
    public Optional<Monografia> findById(Long id) {
        return monografiaRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Monografia> findAll() {
        return monografiaRepository.findAll();
    }

    /**
     *
     * @param monografia
     * @return
     */
    @Transactional
    public Monografia update(Monografia monografia) {
        return monografiaRepository.save(monografia);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
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

    /**
     *
     * @param monografia
     * @return
     */

    public Obra findObraPorMonografia(Monografia monografia) {
        return monografia.getObra();
    }

    /**
     *
     * @param titulo
     * @return
     */
    public List<Monografia> findByTitulo(String titulo) {

        return monografiaRepository.findByTitulo(titulo);
    }

    /**
     *
     * @param idioma
     * @return
     */
    public List<Monografia> findByIdioma(String idioma) {
        return monografiaRepository.findByIdioma(idioma);
    }

    /**
     *
     * @param areacientifica
     * @return
     */
    public List<Monografia> findByAreaCientifica(String areacientifica) {

        return monografiaRepository.findByAreaCientifica(areacientifica);

    }

    /**
     *
     * @param faculdade
     * @return
     */
    public List<Monografia> findByFaculdade(String faculdade) {
        return monografiaRepository.findByFaculdade(faculdade);
    }

    /**
     *
     * @param curso
     * @return
     */
    public List<Monografia> findByCurso(String curso) {
        return monografiaRepository.findByCurso(curso);
    }

    /**
     *
     * @param supervisor
     * @return
     */
    public List<Monografia> findBySupervisor(String supervisor) {
        return monografiaRepository.findBySupervisor(supervisor);
    }
    private static final Logger LOG = Logger.getLogger(MonografiaService.class.getName());

}
