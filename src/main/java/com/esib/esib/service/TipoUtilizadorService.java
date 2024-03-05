package com.esib.esib.service;

import com.esib.esib.model.TipoUtilizador;
import com.esib.esib.model.Utilizador;
import com.esib.esib.repository.TipoUtilizadorRepository;
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
public class TipoUtilizadorService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoUtilizadorService.class.getName());

    /**
     *
     */
    private final TipoUtilizadorRepository tipoUtilizadorRepository;

    // CRUD methods
    /**
     *
     * @param tipoUtilizador
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    public Optional<TipoUtilizador> findById(Long id) {
        return tipoUtilizadorRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<TipoUtilizador> findAll() {
        return tipoUtilizadorRepository.findAll();
    }

    /**
     *
     * @param tipoUtilizador
     * @return
     */
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

    /**
     *
     * @param id
     */
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
    /**
     *
     * @param tipoUtilizador
     * @return
     */
    public List<Utilizador> findUtilizadoresPorTipoUtilizador(TipoUtilizador tipoUtilizador) {
        return tipoUtilizador.getUtilizadorList();
    }

    /**
     *
     * @param tipoUtilizador
     * @return
     */
    public TipoUtilizador findByDescricao(String tipoUtilizador) {
        return tipoUtilizadorRepository.findByDescricao(tipoUtilizador);
    }

}
