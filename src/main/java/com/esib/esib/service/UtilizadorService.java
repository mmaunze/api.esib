package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.UtilizadorRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;



    public Utilizador create(Utilizador utilizador) {
      
        return utilizadorRepository.save(utilizador);
    }

    public Optional<Utilizador> findById(Long id) {
        return utilizadorRepository.findById(id);
    }

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> findUtilizadorPorContacto(String contacto) {
        return utilizadorRepository.findByContacto(contacto);
    }

    public Optional<Utilizador> findUtilizadorPorEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    public Optional<Utilizador> findUtilizadorPorUsername(String username) {
        return utilizadorRepository.findByUsername(username);
    }

    public Utilizador update(Utilizador utilizador) {

        return utilizadorRepository.save(utilizador);
    }

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

    public List<Emprestimo> findEmprestimosPorUtilizador(Utilizador utilizador) {
        return utilizador.getEmprestimoList();
    }

    public AreaCientifica findAreaCientificaPorUtilizador(Utilizador utilizador) {
        return utilizador.getAreaCientifica();
    }

    public Departamento findDepartamentoPorUtilizador(Utilizador utilizador) {
        return utilizador.getDepartamento();
    }

    public TipoUtilizador findTipoUtilizadorPorUtilizador(Utilizador utilizador) {
        return utilizador.getTipoUtilizador();
    }

    public List<Reserva> findReservasPorUtilizador(Utilizador utilizador) {
        return utilizador.getReservaList();
    }

    public List<Utilizador> findByAreaCientifica(String areacientifica) {
        return utilizadorRepository.findByAreaCientifica(areacientifica);
    }

    public List<Utilizador> findByDepartamento(String departamento) {

        return utilizadorRepository.findByDepartamento(departamento);

    }

    public List<Utilizador> findByTipoUtilizador(String tipoutilizador) {
        return utilizadorRepository.findByTipoUtilizador(tipoutilizador);

    }

}