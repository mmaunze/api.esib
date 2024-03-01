package com.esib.esib.service;

import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.DepartamentoRepository;
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
public class DepartamentoService {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(DepartamentoService.class.getName());

    /**
     *
     */
    private final DepartamentoRepository departamentoRepository;

    // CRUD methods

    /**
     *
     * @param departamento
     * @return
     */

    @Transactional
    public Departamento create(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    /**
     *
     * @param departamento
     * @return
     */
    @Transactional
    public Departamento update(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    // Methods related to relationships

    /**
     *
     * @param departamento
     * @return
     */

    public List<Utilizador> findUtilizadoresPorDepartamento(Departamento departamento) {
        return departamento.getUtilizadorList();
    }

    /**
     *
     * @param departamento
     * @return
     */
    public Departamento findByDescricao(String departamento) {
        return departamentoRepository.findByDescricao(departamento);
    }


}
