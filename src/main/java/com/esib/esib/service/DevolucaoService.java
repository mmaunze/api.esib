package com.esib.esib.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.repository.DevolucaoRepository;
import com.esib.esib.repository.EmprestimoRepository;

@Service
public class DevolucaoService {

    @Autowired
    private DevolucaoRepository devolucaoRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // CRUD methods

    @Transactional
    public Devolucao criarDevolucao(Devolucao devolucao) {
        // Verifique se o emprestimo associado existe e está ativo
        Emprestimo emprestimo = devolucao.getEmprestimo();
        if (emprestimo == null || !emprestimo.isAtivo()) {
            throw new RuntimeException("Emprestimo não encontrado ou já devolvido");
        }

        // Calcule o atraso (se houver)
        long diasAtraso = calcularDiasAtraso(emprestimo.getDataEmprestimo(), devolucao.getDataDevolucao());
        devolucao.setAtraso(diasAtraso);

        // Atualize o status do emprestimo para devolvido
        emprestimo.setAtivo(false);

        // Salve a devolucao e atualize o emprestimo
        devolucaoRepository.save(devolucao);
        emprestimoRepository.save(emprestimo);

        return devolucao;
    }

    public Optional<Devolucao> buscarDevolucaoPorId(Long id) {
        return devolucaoRepository.findById(id);
    }

    public List<Devolucao> buscarTodasDevolucoes() {
        return devolucaoRepository.findAll();
    }

    public List<Devolucao> buscarDevolucoesPorBibliotecario(Long bibliotecario) {
        return devolucaoRepository.findByBibliotecario(bibliotecario);
    }

    @Transactional
    public Devolucao atualizarDevolucao(Devolucao devolucao) {
        return devolucaoRepository.save(devolucao);
    }

    @Transactional
    public void excluirDevolucao(Long id) {
        devolucaoRepository.deleteById(id);
    }

    // Method to calculate delay in days
    private long calcularDiasAtraso(Date dataEmprestimo, Date dataDevolucao) {
        long milisegundosNoDia = 24 * 60 * 60 * 1000;
        return (dataDevolucao.getTime() - dataEmprestimo.getTime()) / milisegundosNoDia;
    }

}
