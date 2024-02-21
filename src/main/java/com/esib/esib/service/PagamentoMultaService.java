package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.PagamentoMultaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class PagamentoMultaService {

    private final PagamentoMultaRepository pagamentoMultaRepository;

    // CRUD methods

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

    public Optional<PagamentoMulta> findById(Long id) {
        return pagamentoMultaRepository.findById(id);
    }

    public List<PagamentoMulta> findAll() {
        return pagamentoMultaRepository.findAll();
    }

    public List<PagamentoMulta> findByMulta(Long multa) {
        return pagamentoMultaRepository.findByMulta(multa);
    }

    public List<PagamentoMulta> findByBibliotecario(Long bibliotecario) {
        return pagamentoMultaRepository.findByBibliotecario(bibliotecario);
    }

    public List<PagamentoMulta> findByUtilizador(Long utilizador) {
        return pagamentoMultaRepository.findByUtilizador(utilizador);
    }


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

    @Transactional
    public void delete(Long id) {

        pagamentoMultaRepository.deleteById(id);
    }


    // Methods related to relationships

    public Multa findMultaPorPagamentoMulta(PagamentoMulta pagamentoMulta) {
        return pagamentoMulta.getMulta();
    }

    public Bibliotecario findBibliotecarioPorPagamentoMulta(PagamentoMulta pagamentoMulta) {
        return pagamentoMulta.getBibliotecario();
    }

}
