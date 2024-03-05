package com.esib.esib.service;

import com.esib.esib.model.Bibliotecario;
import com.esib.esib.model.Devolucao;
import com.esib.esib.model.Emprestimo;
import com.esib.esib.model.PagamentoMulta;
import com.esib.esib.repository.BibliotecarioRepository;
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
public class BibliotecarioService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(BibliotecarioService.class.getName());

    /**
     *
     */
    private final BibliotecarioRepository bibliotecarioRepository;

    /**
     *
     */
    private final EstadoRepository estadoRepository;

    // CRUD methods
    /**
     *
     * @param bibliotecario
     * @return
     */
    @Transactional
    public Bibliotecario create(Bibliotecario bibliotecario) {

        return bibliotecarioRepository.save(bibliotecario);
    }

    /**
     *
     * @return
     */
    public List<Bibliotecario> findAll() {
        return bibliotecarioRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Bibliotecario> findById(Long id) {

        return bibliotecarioRepository.findById(id);
    }

    /**
     *
     * @param faculdade
     * @return
     */
    public List<Bibliotecario> findBibliotecariosPorFaculdade(String faculdade) {
        return bibliotecarioRepository.findByIdFaculdade(faculdade);
    }

    /**
     *
     * @param nome
     * @return
     */
    public List<Bibliotecario> findBibliotecariosPorNome(String nome) {

        return bibliotecarioRepository.findByutilizadorNome(nome);
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    @Transactional
    public Bibliotecario update(Bibliotecario bibliotecario) {
        findById(bibliotecario.getId());
        return bibliotecarioRepository.save(bibliotecario);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        bibliotecarioRepository.deleteById(id);
    }

    // Specific operations methods
    /**
     *
     * @param emprestimo
     * @return
     */
    @Transactional
    public Emprestimo registrarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setBibliotecario(findById(emprestimo.getBibliotecario().getId())
                .orElseThrow(() -> new RuntimeException("Bibliotecario não encontrado")));
        return emprestimo;
    }

    /**
     *
     * @param devolucao
     * @return
     */
    @Transactional
    public Devolucao registrarDevolucao(Devolucao devolucao) {
        Emprestimo emprestimo = devolucao.getEmprestimo();
        emprestimo.setEstado(estadoRepository.findByDescricao("devolvido"));
        if (emprestimo.getBibliotecario() != null) {
            bibliotecarioRepository.save(emprestimo.getBibliotecario()); // Update bibliotecario with updated emprestimo
        }
        return devolucao;
    }

    /**
     *
     * @param pagamentoMulta
     * @return
     */
    @Transactional
    public PagamentoMulta registrarPagamentoMulta(PagamentoMulta pagamentoMulta) {
        bibliotecarioRepository.save(pagamentoMulta.getBibliotecario()); // Update bibliotecario with updated
        // pagamentoMulta
        return pagamentoMulta;
    }

    /**
     *
     * @param contacto
     * @return
     */
    public Optional<Bibliotecario> findBibliotecarioPorContacto(String contacto) {
        return bibliotecarioRepository.findBibliotecarioPorContacto(contacto);
    }

    /**
     *
     * @param email
     * @return
     */
    public Optional<Bibliotecario> findBibliotecarioPorEmail(String email) {
        return bibliotecarioRepository.findBibliotecarioPorEmail(email);
    }

    /**
     *
     * @param username
     * @return
     */
    public Optional<Bibliotecario> findBibliotecarioPorUsername(String username) {
        return bibliotecarioRepository.findBibliotecarioPorUsername(username);

    }

}
