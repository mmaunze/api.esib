package com.esib.esib.service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.MultaRepository;
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
public class MultaService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(MultaService.class.getName());

    /**
     *
     */
    private final MultaRepository multaRepository;

    // CRUD methods
    /**
     *
     * @param multa
     * @return
     */
    @Transactional
    public Multa create(Multa multa) {
        // Verifique se as entidades associadas existem
        Emprestimo emprestimo = multa.getEmprestimo();
        Estado estado = multa.getEstado();

        if (emprestimo == null || estado == null) {
            throw new IllegalArgumentException("Entidades associadas à multa não informadas");
        }

        // Verifique se o empréstimo ainda está ativo
        if (!emprestimo.isAtivo()) {
            throw new RuntimeException("Não é possível gerar multa para empréstimo finalizado");
        }

        // Salve a multa
        return multaRepository.save(multa);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Multa> findById(Long id) {
        return multaRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Multa> findAll() {
        return multaRepository.findAll();
    }

    /**
     *
     * @param emprestimo
     * @return
     */
    public List<Multa> findMultasPorEmprestimo(Long emprestimo) {
        return multaRepository.findByEmprestimo(emprestimo);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Multa> findByEstado(String estado) {
        return multaRepository.findByEstado(estado);
    }

    /**
     *
     * @param multa
     * @return
     */
    @Transactional
    public Multa update(Multa multa) {
        return multaRepository.save(multa);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        multaRepository.deleteById(id);
    }

    // Methods related to relationships
    /**
     *
     * @param multa
     * @return
     */
    public Emprestimo findEmprestimoPorMulta(Multa multa) {
        return multa.getEmprestimo();
    }

    /**
     *
     * @param multa
     * @return
     */
    public Estado findEstadoPorMulta(Multa multa) {
        return multa.getEstado();
    }

    /**
     *
     * @param multa
     * @return
     */
    public List<PagamentoMulta> findPagamentosPorMulta(Multa multa) {
        return multa.getPagamentoMultaList();
    }

}
