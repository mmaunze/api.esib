package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.BibliotecarioRepository;
import com.esib.esib.repository.EstadoRepository;

@Service
public class BibliotecarioService {

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;
    private EstadoRepository estadoRepository;

    // CRUD methods

    @Transactional
    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) {

        return bibliotecarioRepository.save(bibliotecario);
    }

    public Optional<Bibliotecario> buscarBibliotecarioPorId(Long id) {

        return bibliotecarioRepository.findById(id);
    }

    public List<Bibliotecario> buscarBibliotecariosPorFaculdade(String faculdade) {
        return bibliotecarioRepository.findByIdFaculdade(faculdade);
    }

    public List<Bibliotecario> buscarBibliotecariosPorNome(String nome) {

        return bibliotecarioRepository.findByutilizadorNome(nome);
    }

    @Transactional
    public Bibliotecario atualizarBibliotecario(Bibliotecario bibliotecario) {
        buscarBibliotecarioPorId(bibliotecario.getId());
        return bibliotecarioRepository.save(bibliotecario);
    }

    @Transactional
    public void excluirBibliotecario(Long id) {
        bibliotecarioRepository.deleteById(id);
    }

    // Specific operations methods

    @Transactional
    public Emprestimo registrarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setBibliotecario(buscarBibliotecarioPorId(emprestimo.getBibliotecario().getId())
                .orElseThrow(() -> new RuntimeException("Bibliotecario não encontrado")));
        return emprestimo;
    }

    @Transactional
    public Devolucao registrarDevolucao(Devolucao devolucao) {
        Emprestimo emprestimo = devolucao.getEmprestimo();
        emprestimo.setEstado(estadoRepository.findByDescricao("devolvido"));
        bibliotecarioRepository.save(emprestimo.getBibliotecario()); // Update bibliotecario with updated emprestimo
        return devolucao;
    }

    @Transactional
    public PagamentoMulta registrarPagamentoMulta(PagamentoMulta pagamentoMulta) {
        bibliotecarioRepository.save(pagamentoMulta.getBibliotecario()); // Update bibliotecario with updated
                                                                         // pagamentoMulta
        return pagamentoMulta;
    }

}
