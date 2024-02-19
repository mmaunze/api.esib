package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.TipoMovimento;
import com.esib.esib.repository.TipoMovimentoRepository;

@Service
public class TipoMovimentoService {

    @Autowired
    private TipoMovimentoRepository tipoMovimentoRepository;

    // CRUD methods

    @Transactional
    public TipoMovimento criarTipoMovimento(TipoMovimento tipoMovimento) {
        // Verifique se a descrição do tipo de movimento já existe
        /*
         * String descricao = tipoMovimento.getDescricao();
         * if (tipoMovimentoRepository.existsByDescricao(descricao)) {
         * throw new
         * RuntimeException("Tipo de movimento com a descrição informada já existe");
         * }
         */
        // Salve o tipo de movimento
        return tipoMovimentoRepository.save(tipoMovimento);
    }

    public Optional<TipoMovimento> buscarTipoMovimentoPorId(Long id) {
        return tipoMovimentoRepository.findById(id);
    }

    public List<TipoMovimento> buscarTodosTiposMovimentos() {
        return tipoMovimentoRepository.findAll();
    }

    public TipoMovimento buscarTipoMovimentoPorDescricao(String descricao) {
        return tipoMovimentoRepository.findByDescricao(descricao);
    }

    @Transactional
    public TipoMovimento atualizarTipoMovimento(TipoMovimento tipoMovimento) {
        // Verifique se a descrição do tipo de movimento já existe (se alterada)
        /*
         * if (!tipoMovimento.getDescricao().equals(tipoMovimentoRepository.findById(
         * tipoMovimento.getIdTipoMovimento()).get().getDescricao())) {
         * if (tipoMovimentoRepository.existsByDescricao(tipoMovimento.getDescricao()))
         * {
         * throw new
         * RuntimeException("Tipo de movimento com a descrição informada já existe");
         * }
         * }
         */
        return tipoMovimentoRepository.save(tipoMovimento);
    }

    @Transactional
    public void excluirTipoMovimento(Long id) {
        // Verifique se o tipo de movimento está associado a algum movimento antes de
        // excluir
        var tipoMovimento = tipoMovimentoRepository.findById(id).get();
        List<Movimento> movimentos = tipoMovimento.getMovimentoList();

        if (!movimentos.isEmpty()) {
            throw new RuntimeException("Tipo de movimento está associado a movimentações e não pode ser excluído");
        }

        tipoMovimentoRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Movimento> buscarMovimentosPorTipoMovimento(TipoMovimento tipoMovimento) {
        return tipoMovimento.getMovimentoList();
    }

}
