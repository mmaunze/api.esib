package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.repository.BibliotecarioRepository;
import com.esib.esib.repository.EstadoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class BibliotecarioService {

    private final BibliotecarioRepository bibliotecarioRepository;

    private final EstadoRepository estadoRepository;

    // CRUD methods

    @Transactional
    public Bibliotecario create(Bibliotecario bibliotecario) {

        return bibliotecarioRepository.save(bibliotecario);
    }

    public List<Bibliotecario> findAll() {
        return bibliotecarioRepository.findAll();
    }

    public Optional<Bibliotecario> findById(Long id) {

        return bibliotecarioRepository.findById(id);
    }

    public List<Bibliotecario> findBibliotecariosPorFaculdade(String faculdade) {
        return bibliotecarioRepository.findByIdFaculdade(faculdade);
    }

    public List<Bibliotecario> findBibliotecariosPorNome(String nome) {

        return bibliotecarioRepository.findByutilizadorNome(nome);
    }

    @Transactional
    public Bibliotecario update(Bibliotecario bibliotecario) {
        findById(bibliotecario.getId());
        return bibliotecarioRepository.save(bibliotecario);
    }

    @Transactional
    public void delete(Long id) {
        bibliotecarioRepository.deleteById(id);
    }

    // Specific operations methods

    @Transactional
    public Emprestimo registrarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setBibliotecario(findById(emprestimo.getBibliotecario().getId())
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

    public Optional<Bibliotecario> findBibliotecarioPorContacto(String contacto) {
        return bibliotecarioRepository.findBibliotecarioPorContacto(contacto);
    }

    public Optional<Bibliotecario> findBibliotecarioPorEmail(String email) {
        return bibliotecarioRepository.findBibliotecarioPorEmail(email);
    }

    public Optional<Bibliotecario> findBibliotecarioPorUsername(String username) {
        return bibliotecarioRepository.findBibliotecarioPorUsername(username);

    }

}
