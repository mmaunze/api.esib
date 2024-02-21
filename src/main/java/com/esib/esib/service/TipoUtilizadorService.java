package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.TipoUtilizadorRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class TipoUtilizadorService {

    private final TipoUtilizadorRepository tipoUtilizadorRepository;

    // CRUD methods

    @Transactional
    public TipoUtilizador create(TipoUtilizador tipoUtilizador) {
        // Verifique se a descrição do tipo de utilizador já existe
        /*
         * String descricao = tipoUtilizador.getDescricao();
         * if (tipoUtilizadorRepository.existsByDescricao(descricao)) {
         * throw new
         * RuntimeException("Tipo de utilizador com a descrição informada já existe");
         * }
         */
        // Salve o tipo de utilizador
        return tipoUtilizadorRepository.save(tipoUtilizador);
    }

    public Optional<TipoUtilizador> findById(Long id) {
        return tipoUtilizadorRepository.findById(id);
    }

    public List<TipoUtilizador> findAll() {
        return tipoUtilizadorRepository.findAll();
    }

    @Transactional
    public TipoUtilizador update(TipoUtilizador tipoUtilizador) {
        // Verifique se a descrição do tipo de utilizador já existe (se alterada)
        /*
         * if (tipoUtilizador.getDescricao() != null &&
         * !tipoUtilizador.getDescricao().equals(tipoUtilizadorRepository.findById(
         * tipoUtilizador.getIdTipoUtilizador()).get().getDescricao())) {
         * if
         * (tipoUtilizadorRepository.existsByDescricao(tipoUtilizador.getDescricao())) {
         * throw new
         * RuntimeException("Tipo de utilizador com a descrição informada já existe");
         * }
         * }
         */
        return tipoUtilizadorRepository.save(tipoUtilizador);
    }

    @Transactional
    public void delete(Long id) {
        // Verifique se o tipo de utilizador está associado a algum utilizador antes de
        // excluir
        var tipoUtilizador = tipoUtilizadorRepository.findById(id).get();
        List<Utilizador> utilizadores = tipoUtilizador.getUtilizadorList();

        if (!utilizadores.isEmpty()) {
            throw new RuntimeException("Tipo de utilizador está associado a utilizadores e não pode ser excluído");
        }

        tipoUtilizadorRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Utilizador> findUtilizadoresPorTipoUtilizador(TipoUtilizador tipoUtilizador) {
        return tipoUtilizador.getUtilizadorList();
    }

    public TipoUtilizador findByDescricao(String tipoUtilizador) {
        return tipoUtilizadorRepository.findByDescricao(tipoUtilizador);
    }

}
