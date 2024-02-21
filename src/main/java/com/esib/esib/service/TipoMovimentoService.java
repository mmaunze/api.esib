package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.TipoMovimento;
import com.esib.esib.repository.TipoMovimentoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class TipoMovimentoService {

    private final TipoMovimentoRepository tipoMovimentoRepository;

    // CRUD methods

    @Transactional
    public TipoMovimento create(TipoMovimento tipoMovimento) {
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

    public Optional<TipoMovimento> findById(Long id) {
        return tipoMovimentoRepository.findById(id);
    }

    public List<TipoMovimento> findAll() {
        return tipoMovimentoRepository.findAll();
    }

    public TipoMovimento findTipoMovimentoPorDescricao(String descricao) {
        return tipoMovimentoRepository.findByDescricao(descricao);
    }

    @Transactional
    public TipoMovimento update(TipoMovimento tipoMovimento) {
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
    public void delete(Long id) {
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

    public List<Movimento> findMovimentosPorTipoMovimento(TipoMovimento tipoMovimento) {
        return tipoMovimento.getMovimentoList();
    }

    public TipoMovimento findByDescricao(String tipoMovimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
