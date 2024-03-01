package com.esib.esib.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.modelo.enums.ProfileEnum;
import com.esib.esib.repository.UtilizadorRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Meldo Maunze
 */
@Service
@RequiredArgsConstructor
@Data
public class UtilizadorService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UtilizadorRepository utilizadorRepository;

    /**
     *
     * @param utilizador
     * @return
     */
    public Utilizador create(Utilizador utilizador) {

        return utilizadorRepository.save(utilizador);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Utilizador> findById(Long id) {
        return utilizadorRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    /**
     *
     * @param contacto
     * @return
     */
    public Optional<Utilizador> findUtilizadorPorContacto(String contacto) {
        return utilizadorRepository.findByContacto(contacto);
    }

    /**
     *
     * @param email
     * @return
     */
    public Optional<Utilizador> findUtilizadorPorEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    /**
     *
     * @param username
     * @return
     */
    public Optional<Utilizador> findUtilizadorPorUsername(String username) {
        return utilizadorRepository.findByUsername(username);
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public Utilizador update(Utilizador utilizador) {

        utilizador.setId(null);
        utilizador.setPassword(this.bCryptPasswordEncoder.encode(utilizador.getPassword()));

        utilizador.setProfiles(Stream.of(ProfileEnum.ADMINISTRADOR.getCode()).collect(Collectors.toSet()));
        return utilizadorRepository.save(utilizador);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        // Verifique se o utilizador tem emprestimos em aberto antes de excluir
        var utilizador = utilizadorRepository.findById(id).get();
        List<Emprestimo> emprestimos = utilizador.getEmprestimoList();
        emprestimos.stream().filter(emprestimo -> (emprestimo.isAtivo())).forEachOrdered(_item -> {
            throw new RuntimeException("Utilizador possui empréstimos em aberto e não pode ser excluído");
        });

        // Verifique se o utilizador é bibliotecário ou estudante antes de excluir
        if (utilizador.getBibliotecario() != null || utilizador.getEstudante() != null) {
            throw new RuntimeException("Utilizador é bibliotecário ou estudante e não pode ser excluído diretamente");
        }

        utilizadorRepository.deleteById(id);
    }

    // Methods related to relationships
    /**
     *
     * @param utilizador
     * @return
     */
    public List<Emprestimo> findEmprestimosPorUtilizador(Utilizador utilizador) {
        return utilizador.getEmprestimoList();
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public AreaCientifica findAreaCientificaPorUtilizador(Utilizador utilizador) {
        return utilizador.getAreaCientifica();
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public Departamento findDepartamentoPorUtilizador(Utilizador utilizador) {
        return utilizador.getDepartamento();
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public TipoUtilizador findTipoUtilizadorPorUtilizador(Utilizador utilizador) {
        return utilizador.getTipoUtilizador();
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public List<Reserva> findReservasPorUtilizador(Utilizador utilizador) {
        return utilizador.getReservaList();
    }

    /**
     *
     * @param areacientifica
     * @return
     */
    public List<Utilizador> findByAreaCientifica(String areacientifica) {
        return utilizadorRepository.findByAreaCientifica(areacientifica);
    }

    /**
     *
     * @param departamento
     * @return
     */
    public List<Utilizador> findByDepartamento(String departamento) {

        return utilizadorRepository.findByDepartamento(departamento);

    }

    /**
     *
     * @param tipoutilizador
     * @return
     */
    public List<Utilizador> findByTipoUtilizador(String tipoutilizador) {
        return utilizadorRepository.findByTipoUtilizador(tipoutilizador);

    }
    private static final Logger LOG = Logger.getLogger(UtilizadorService.class.getName());

}
