package com.esib.esib.service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.PagamentoMultaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoMultaService {

    @Autowired
    private PagamentoMultaRepository pagamentoMultaRepository;

    // CRUD methods

    @Transactional
    public PagamentoMulta criarPagamentoMulta(PagamentoMulta pagamentoMulta) {
        // Verifique se a multa associada existe
        Multa multa = pagamentoMulta.getIdMulta();
        if (multa == null) {
            throw new RuntimeException("Multa associada ao pagamento não informada");
        }

        // Verifique se o valor pago corresponde ao valor da multa
        if (pagamentoMulta.getValorPago() != multa.getValorMulta()) {
            throw new RuntimeException("Valor pago difere do valor da multa");
        }

        // Verifique se a multa já possui pagamento registrado
        if (multa.getIdEstado().getIdEstado() == 2) { // Estado da multa = Paga
            throw new RuntimeException("Multa já possui pagamento registrado");
        }

        // Atualize o estado da multa para paga
        multa.getIdEstado().setPaga(); // Estado da multa = Paga

        return pagamentoMultaRepository.save(pagamentoMulta);
    }

    public Optional<PagamentoMulta> buscarPagamentoMultaPorId(Long id) {
        return pagamentoMultaRepository.findById(id);
    }

    public List<PagamentoMulta> buscarTodosPagamentosMultas() {
        return pagamentoMultaRepository.findAll();
    }

    public List<PagamentoMulta> buscarPagamentosPorMulta(Long multa) {
        return pagamentoMultaRepository.findByMulta(multa);
    }

    public List<PagamentoMulta> buscarPagamentosPorBibliotecario(Long bibliotecario) {
        return pagamentoMultaRepository.findByBibliotecario(bibliotecario);
    }

    // Methods related to relationships

    public Multa buscarMultaPorPagamentoMulta(PagamentoMulta pagamentoMulta) {
        return pagamentoMulta.getIdMulta();
    }

    public Bibliotecario buscarBibliotecarioPorPagamentoMulta(PagamentoMulta pagamentoMulta) {
        return pagamentoMulta.getIdBibliotecario();
    }

}
