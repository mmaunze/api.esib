package com.esib.esib.service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.repository.EstadoRepository;
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
public class EstadoService {

    private final EstadoRepository estadoRepository;

    // CRUD methods

    /**
     *
     * @param estado
     * @return
     */

    @Transactional
    public Estado create(Estado estado) {
        return estadoRepository.save(estado);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Estado> findById(Long id) {
        return estadoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    /**
     *
     * @param estado
     * @return
     */
    @Transactional
    public Estado update(Estado estado) {
        return estadoRepository.save(estado);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        var estado = estadoRepository.getById(id);
        // Verifique se o estado possui entidades relacionadas antes de excluir
        List<Emprestimo> emprestimos = estado.getEmprestimoList();
        List<Multa> multas = estado.getMultaList();

        List<Obra> obras = estado.getObraList();
        List<Reserva> reservas = estado.getReservaList();

        if (!emprestimos.isEmpty() || !multas.isEmpty() || !obras.isEmpty() || !reservas.isEmpty()) {
            throw new RuntimeException("Estado possui entidades relacionadas e não pode ser excluído");
        }

        estadoRepository.deleteById(id);
    }

    // Methods related to relationships

    /**
     *
     * @param estado
     * @return
     */

    public List<Emprestimo> findEmprestimosPorEstado(Estado estado) {
        return estado.getEmprestimoList();
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Multa> findMultasPorEstado(Estado estado) {
        return estado.getMultaList();
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Obra> findObrasPorEstado(Estado estado) {
        return estado.getObraList();
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Reserva> findReservasPorEstado(Estado estado) {
        return estado.getReservaList();
    }

    /**
     *
     * @param estado
     * @return
     */
    public Estado findByDescricao(String estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }
    private static final Logger LOG = Logger.getLogger(EstadoService.class.getName());

}
