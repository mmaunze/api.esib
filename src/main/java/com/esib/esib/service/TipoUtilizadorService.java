package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.TipoUtilizador;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.TipoUtilizadorRepository;

@Service
public class TipoUtilizadorService {

    @Autowired
    private TipoUtilizadorRepository tipoUtilizadorRepository;

    // CRUD methods

    @Transactional
    public TipoUtilizador criarTipoUtilizador(TipoUtilizador tipoUtilizador) {
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

    public Optional<TipoUtilizador> buscarTipoUtilizadorPorId(Long id) {
        return tipoUtilizadorRepository.findById(id);
    }

    public List<TipoUtilizador> buscarTodosTiposUtilizadores() {
        return tipoUtilizadorRepository.findAll();
    }

    public TipoUtilizador buscarTipoUtilizadorPorDescricao(String descricao) {
        return tipoUtilizadorRepository.findByDescricao(descricao);
    }

    @Transactional
    public TipoUtilizador atualizarTipoUtilizador(TipoUtilizador tipoUtilizador) {
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
    public void excluirTipoUtilizador(Long id) {
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

    public List<Utilizador> buscarUtilizadoresPorTipoUtilizador(TipoUtilizador tipoUtilizador) {
        return tipoUtilizador.getUtilizadorList();
    }

}
