package com.esib.esib.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.repository.DevolucaoRepository;
import com.esib.esib.repository.EmprestimoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class DevolucaoService {

    private final DevolucaoRepository devolucaoRepository;

    private final EmprestimoRepository emprestimoRepository;

    // CRUD methods

    @Transactional
    public Devolucao create(Devolucao devolucao) {
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

    public Optional<Devolucao> findById(Long id) {
        return devolucaoRepository.findById(id);
    }

    public List<Devolucao> findAll() {
        return devolucaoRepository.findAll();
    }

    public List<Devolucao> findDevolucoesPorBibliotecario(Long bibliotecario) {
        return devolucaoRepository.findByBibliotecario(bibliotecario);
    }

    @Transactional
    public Devolucao update(Devolucao devolucao) {
        return devolucaoRepository.save(devolucao);
    }

    @Transactional
    public void delete(Long id) {
        devolucaoRepository.deleteById(id);
    }

    // Method to calculate delay in days
    private long calcularDiasAtraso(Date dataEmprestimo, Date dataDevolucao) {
        long milisegundosNoDia = 24 * 60 * 60 * 1000;
        return (dataDevolucao.getTime() - dataEmprestimo.getTime()) / milisegundosNoDia;
    }

    public List<Devolucao> findByBibliotecario(Long bibliotecario) {
        return devolucaoRepository.findByBibliotecario(bibliotecario);
    }

    public List<Devolucao> findByUtilizador(Long utilizador) {
        return devolucaoRepository.findByUtilizador(utilizador);
    }

    public List<Devolucao> findByObra(Long obra) {
        return devolucaoRepository.findByObra(obra);
    }

    public List<Devolucao> findByTitulo(String estado) {
        return devolucaoRepository.findByTitulo(estado);
    }

    public List<Devolucao> findByIdioma(String estado) {
        return devolucaoRepository.findByIdioma(estado);
    }

    public List<Devolucao> findByAreaCientifica(String areacientifica) {
        return devolucaoRepository.findByAreaCientifica(areacientifica);

    }

}
