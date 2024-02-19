package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.TipoObra;
import com.esib.esib.repository.TipoObraRepository;

@Service
public class TipoObraService {

    @Autowired
    private TipoObraRepository tipoObraRepository;

    // CRUD methods

    @Transactional
    public TipoObra criarTipoObra(TipoObra tipoObra) {
        // Verifique se a descrição do tipo de obra já existe
        /*
         * String descricao = tipoObra.getDescricao();
         * if (tipoObraRepository.existsByDescricao(descricao)) {
         * throw new
         * RuntimeException("Tipo de obra com a descrição informada já existe");
         * }
         */
        // Salve o tipo de obra
        return tipoObraRepository.save(tipoObra);
    }

    public Optional<TipoObra> buscarTipoObraPorId(Long id) {
        return tipoObraRepository.findById(id);
    }

    public List<TipoObra> buscarTodosTiposObras() {
        return tipoObraRepository.findAll();
    }

    public TipoObra buscarTipoObraPorDescricao(String descricao) {
        return tipoObraRepository.findByDescricao(descricao);
    }

    @Transactional
    public TipoObra atualizarTipoObra(TipoObra tipoObra) {
        // Verifique se a descrição do tipo de obra já existe (se alterada)
        /*
         * if (!tipoObra.getDescricao().equals(tipoObraRepository.findById(tipoObra.
         * getIdTipoObra()).get().getDescricao())) {
         * if (tipoObraRepository.existsByDescricao(tipoObra.getDescricao())) {
         * throw new
         * RuntimeException("Tipo de obra com a descrição informada já existe");
         * }
         * }
         */
        return tipoObraRepository.save(tipoObra);
    }

    @Transactional
    public void excluirTipoObra(Long id) {
        // Verifique se o tipo de obra está associado a alguma obra antes de excluir
        var tipoObra = tipoObraRepository.findById(id).get();
        List<Obra> obras = tipoObra.getObraList();

        if (!obras.isEmpty()) {
            throw new RuntimeException("Tipo de obra está associado a obras e não pode ser excluído");
        }

        tipoObraRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Obra> buscarObrasPorTipoObra(TipoObra tipoObra) {
        return tipoObra.getObraList();
    }

}
