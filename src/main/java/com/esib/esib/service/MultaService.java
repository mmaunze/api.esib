package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.MultaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class MultaService {

    private final MultaRepository multaRepository;

    // CRUD methods

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

    public Optional<Multa> findById(Long id) {
        return multaRepository.findById(id);
    }

    public List<Multa> findAll() {
        return multaRepository.findAll();
    }

    public List<Multa> findMultasPorEmprestimo(Long emprestimo) {
        return multaRepository.findByEmprestimo(emprestimo);
    }

    public List<Multa> findByEstado(String estado) {
        return multaRepository.findByEstado(estado);
    }

    @Transactional
    public Multa update(Multa multa) {
        return multaRepository.save(multa);
    }

    @Transactional
    public void delete(Long id) {
        multaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Emprestimo findEmprestimoPorMulta(Multa multa) {
        return multa.getEmprestimo();
    }

    public Estado findEstadoPorMulta(Multa multa) {
        return multa.getEstado();
    }

    public List<PagamentoMulta> findPagamentosPorMulta(Multa multa) {
        return multa.getPagamentoMultaList();
    }
}
