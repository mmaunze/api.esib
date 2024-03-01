package com.esib.esib.service;

import com.esib.esib.modelo.Estudante;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.EstudanteRepository;
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
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;

    // CRUD methods

    /**
     *
     * @param estudante
     * @return
     */

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

    /**
     *
     * @param id
     * @return
     */
    public Optional<Estudante> findById(Long id) {
        return estudanteRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Estudante> findAll() {
        return estudanteRepository.findAll();
    }

    /**
     *
     * @param nivel
     * @return
     */
    public List<Estudante> findEstudantesPorNivel(int nivel) {
        return estudanteRepository.findByNivel(nivel);
    }

    /**
     *
     * @param estudante
     * @return
     */
    @Transactional
    public Estudante update(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        estudanteRepository.deleteById(id);
    }

    // Method related to relationship

    /**
     *
     * @param estudante
     * @return
     */

    public Utilizador findUtilizadorPorEstudante(Estudante estudante) {
        return estudante.getUtilizador();
    }

    /**
     *
     * @param username
     * @return
     */
    public Optional<Estudante> findByUsername(String username) {
        return estudanteRepository.findByUsername(username);
    }

    /**
     *
     * @param contacto
     * @return
     */
    public Optional<Estudante> findByContacto(String contacto) {

        return estudanteRepository.findByContacto(contacto);
    }

    /**
     *
     * @param email
     * @return
     */
    public Optional<Estudante> findByEmail(String email) {
        return estudanteRepository.findByEmail(email);
    }

    /**
     *
     * @param curso
     * @return
     */
    public Optional<Estudante> findByCurso(String curso) {
        return estudanteRepository.findByCurso(curso);
    }
    private static final Logger LOG = Logger.getLogger(EstudanteService.class.getName());

}
