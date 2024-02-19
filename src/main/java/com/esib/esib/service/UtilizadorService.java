package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.UtilizadorRepository;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // CRUD methods

    public Utilizador create(Utilizador utilizador) {
        /*
         * validarUtilizador(utilizador);
         * 
         * // Criptografe a senha antes de salvar
         * utilizador.setSenha(passwordEncoder.encode(utilizador.getSenha()));
         * 
         * // Salve o utilizador
         */
        return utilizadorRepository.save(utilizador);
    }

    public Optional<Utilizador> buscarUtilizadorPorId(Long id) {
        return utilizadorRepository.findById(id);
    }

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Utilizador buscarUtilizadorPorContacto(String contacto) {
        return utilizadorRepository.findByContacto(contacto);
    }

    public Utilizador buscarUtilizadorPorEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    public Utilizador buscarUtilizadorPorUsername(String username) {
        return utilizadorRepository.findByUsername(username);
    }

    public Utilizador update(Utilizador utilizador) {

        return utilizadorRepository.save(utilizador);
    }

    /*
     * @Transactional
     * public Utilizador atualizarUtilizador(Utilizador utilizador) {
     * validarUtilizador(utilizador);
     * 
     * // Criptografe a senha antes de salvar (se a senha foi alterada)
     * Utilizador utilizadorExistente =
     * utilizadorRepository.findById(utilizador.getIdUtilizador()).get();
     * if (!utilizador.getSenha().equals(utilizadorExistente.getSenha())) {
     * utilizador.setSenha(passwordEncoder.encode(utilizador.getSenha()));
     * }
     * 
     * // Salve o utilizador
     * return utilizadorRepository.save(utilizador);
     * }
     */
    @Transactional
    public void delete(Long id) {
        // Verifique se o utilizador tem emprestimos em aberto antes de excluir
        var utilizador = utilizadorRepository.findById(id).get();
        List<Emprestimo> emprestimos = utilizador.getEmprestimoList();
        for (var emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) {
                throw new RuntimeException("Utilizador possui empréstimos em aberto e não pode ser excluído");
            }
        }

        // Verifique se o utilizador é bibliotecário ou estudante antes de excluir
        if (utilizador.getBibliotecario() != null || utilizador.getEstudante() != null) {
            throw new RuntimeException("Utilizador é bibliotecário ou estudante e não pode ser excluído diretamente");
        }

        utilizadorRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Emprestimo> buscarEmprestimosPorUtilizador(Utilizador utilizador) {
        return utilizador.getEmprestimoList();
    }

    public AreaCientifica buscarAreaCientificaPorUtilizador(Utilizador utilizador) {
        return utilizador.getIdArea();
    }

    public Departamento buscarDepartamentoPorUtilizador(Utilizador utilizador) {
        return utilizador.getIdDepartamento();
    }

    public TipoUtilizador buscarTipoUtilizadorPorUtilizador(Utilizador utilizador) {
        return utilizador.getIdTipoUtilizador();
    }

    public List<Reserva> buscarReservasPorUtilizador(Utilizador utilizador) {
        return utilizador.getReservaList();
    }
}