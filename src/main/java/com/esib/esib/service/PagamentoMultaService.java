package com.esib.esib.service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.PagamentoMultaRepository;
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
public class PagamentoMultaService {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(PagamentoMultaService.class.getName());

    /**
     *
     */
    private final PagamentoMultaRepository pagamentoMultaRepository;

    // CRUD methods

    /**
     *
     * @param pagamentoMulta
     * @return
     */

    @Transactional
    public PagamentoMulta create(PagamentoMulta pagamentoMulta) {
        // Verifique se a multa associada existe
        Multa multa = pagamentoMulta.getMulta();
        if (multa == null) {
            throw new RuntimeException("Multa associada ao pagamento não informada");
        }

        // Verifique se o valor pago corresponde ao valor da multa
        if (pagamentoMulta.getValorPago() != multa.getValorMulta()) {
            throw new RuntimeException("Valor pago difere do valor da multa");
        }

        // Verifique se a multa já possui pagamento registrado
        if (multa.getEstado().getId() == 2) { // Estado da multa = Paga
            throw new RuntimeException("Multa já possui pagamento registrado");
        }

        // Atualize o estado da multa para paga
        multa.getEstado().setPaga(); // Estado da multa = Paga

        return pagamentoMultaRepository.save(pagamentoMulta);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<PagamentoMulta> findById(Long id) {
        return pagamentoMultaRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<PagamentoMulta> findAll() {
        return pagamentoMultaRepository.findAll();
    }

    /**
     *
     * @param multa
     * @return
     */
    public List<PagamentoMulta> findByMulta(Long multa) {
        return pagamentoMultaRepository.findByMulta(multa);
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    public List<PagamentoMulta> findByBibliotecario(Long bibliotecario) {
        return pagamentoMultaRepository.findByBibliotecario(bibliotecario);
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public List<PagamentoMulta> findByUtilizador(Long utilizador) {
        return pagamentoMultaRepository.findByUtilizador(utilizador);
    }

    /**
     *
     * @param pagamentoMulta
     * @return
     */
    @Transactional
    public PagamentoMulta update(PagamentoMulta pagamentoMulta) {
        // Verifique se a multa associada existe
        Multa multa = pagamentoMulta.getMulta();
        if (multa == null) {
            throw new RuntimeException("Multa associada ao pagamento não informada");
        }

        // Verifique se o valor pago corresponde ao valor da multa
        if (pagamentoMulta.getValorPago() != multa.getValorMulta()) {
            throw new RuntimeException("Valor pago difere do valor da multa");
        }

        // Verifique se a multa já possui pagamento registrado
        if (multa.getEstado().getId() == 2) { // Estado da multa = Paga
            throw new RuntimeException("Multa já possui pagamento registrado");
        }

        // Atualize o estado da multa para paga
        multa.getEstado().setPaga(); // Estado da multa = Paga

        return pagamentoMultaRepository.save(pagamentoMulta);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {

        pagamentoMultaRepository.deleteById(id);
    }


    // Methods related to relationships

    /**
     *
     * @param pagamentoMulta
     * @return
     */

    public Multa findMultaPorPagamentoMulta(PagamentoMulta pagamentoMulta) {
        return pagamentoMulta.getMulta();
    }

    /**
     *
     * @param pagamentoMulta
     * @return
     */
    public Bibliotecario findBibliotecarioPorPagamentoMulta(PagamentoMulta pagamentoMulta) {
        return pagamentoMulta.getBibliotecario();
    }


}
