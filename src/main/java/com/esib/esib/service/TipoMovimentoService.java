package com.esib.esib.service;

import com.esib.esib.model.Movimento;
import com.esib.esib.model.TipoMovimento;
import com.esib.esib.repository.TipoMovimentoRepository;
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
public class TipoMovimentoService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoMovimentoService.class.getName());

    /**
     *
     */
    private final TipoMovimentoRepository tipoMovimentoRepository;

    // CRUD methods
    /**
     *
     * @param tipoMovimento
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    public Optional<TipoMovimento> findById(Long id) {
        return tipoMovimentoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<TipoMovimento> findAll() {
        return tipoMovimentoRepository.findAll();
    }

    /**
     *
     * @param descricao
     * @return
     */
    public TipoMovimento findTipoMovimentoPorDescricao(String descricao) {
        return tipoMovimentoRepository.findByDescricao(descricao);
    }

    /**
     *
     * @param tipoMovimento
     * @return
     */
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

    /**
     *
     * @param id
     */
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
    /**
     *
     * @param tipoMovimento
     * @return
     */
    public List<Movimento> findMovimentosPorTipoMovimento(TipoMovimento tipoMovimento) {
        return tipoMovimento.getMovimentoList();
    }

    /**
     *
     * @param tipoMovimento
     * @return
     */
    public TipoMovimento findByDescricao(String tipoMovimento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
