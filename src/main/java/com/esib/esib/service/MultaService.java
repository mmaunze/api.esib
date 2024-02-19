package com.esib.esib.service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.MultaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    // CRUD methods

    @Transactional
    public Multa criarMulta(Multa multa) {
        // Verifique se as entidades associadas existem
        Emprestimo emprestimo = multa.getIdEmprestimo();
        Estado estado = multa.getIdEstado();

        if (emprestimo == null || estado == null) {
            throw new RuntimeException("Entidades associadas à multa não informadas");
        }

        // Verifique se o empréstimo ainda está ativo
        if (!emprestimo.isAtivo()) {
            throw new RuntimeException("Não é possível gerar multa para empréstimo finalizado");
        }

        // Salve a multa
        return multaRepository.save(multa);
    }

    public Optional<Multa> buscarMultaPorId(Long id) {
        return multaRepository.findById(id);
    }

    public List<Multa> buscarTodasMultas() {
        return multaRepository.findAll();
    }

    public List<Multa> buscarMultasPorEmprestimo(Long emprestimo) {
        return multaRepository.findByEmprestimo(emprestimo);
    }

    public List<Multa> buscarMultasPorEstado(Long estado) {
        return multaRepository.findByEstado(estado);
    }

    @Transactional
    public Multa atualizarMulta(Multa multa) {
        return multaRepository.save(multa);
    }

    @Transactional
    public void excluirMulta(Long id) {
        multaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Emprestimo buscarEmprestimoPorMulta(Multa multa) {
        return multa.getIdEmprestimo();
    }

    public Estado buscarEstadoPorMulta(Multa multa) {
        return multa.getIdEstado();
    }

    public List<PagamentoMulta> buscarPagamentosPorMulta(Multa multa) {
        return multa.getPagamentoMultaList();
    }
}
