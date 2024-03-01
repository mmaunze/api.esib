package com.esib.esib.service;

import com.esib.esib.exceptions.IllegalOrphanException;
import com.esib.esib.exceptions.NonexistentEntityException;
import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.AreaCientificaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 *
 * @author Meldo Maunze
 */
@Service
@RequiredArgsConstructor
@Data
public class AreaCientificaService {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(AreaCientificaService.class.getName());

    /**
     *
     */
    private final AreaCientificaRepository areaCientificaRepository;

    // CRUD methods
    /**
     *
     * @param id
     * @return
     */
    public Optional<AreaCientifica> findById(@NonNull Long id) {
        return areaCientificaRepository.findById(id);
    }

    /**
     *
     * @param areaCientifica
     * @return
     */
    @Transactional
    public AreaCientifica create(AreaCientifica areaCientifica) {

        if (areaCientifica.getObraList() == null) {
            areaCientifica.setObraList(new ArrayList<>());
        }
        if (areaCientifica.getUtilizadorList() == null) {
            areaCientifica.setUtilizadorList(new ArrayList<>());
        }
        for (Obra obra : areaCientifica.getObraList()) {
            obra.setAreaCientifica(areaCientifica);
        }
        for (Utilizador utilizador : areaCientifica.getUtilizadorList()) {
            utilizador.setAreaCientifica(areaCientifica);
        }

        return areaCientificaRepository.save(areaCientifica);
    }

    /**
     *
     * @return
     */
    public List<AreaCientifica> findAll() {
        return areaCientificaRepository.findAll();
    }

    /**
     *
     * @param id
     * @param areaCientifica
     * @return
     */
    @Transactional
    public AreaCientifica update(Long id, AreaCientifica areaCientifica) {
        var newAreaCientifica = new AreaCientifica();
        
        areaCientifica.setId(id);
        newAreaCientifica.setId(id);
        newAreaCientifica.setDescricao(areaCientifica.getDescricao());
        return areaCientificaRepository.save(newAreaCientifica);
    }

    /**
     *
     * @param id
     * @throws com.esib.esib.exceptions.IllegalOrphanException
     * @throws com.esib.esib.exceptions.NonexistentEntityException
     */
    @Transactional
    public void delete(Long id) throws IllegalOrphanException, NonexistentEntityException {

        Optional<AreaCientifica> areaCientifica;
        try {
            areaCientifica = areaCientificaRepository.findById(id);
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The areaCientifica with id " + id + " no longer exists.", enfe);
        }

        List<String> illegalOrphanMessages = null;
        List<Obra> obraListOrphanCheck = null;
        List<Utilizador> utilizadorListOrphanCheck = null;

        if (areaCientifica.isPresent()) {
            obraListOrphanCheck = areaCientifica.get().getObraList();
        }

        if (obraListOrphanCheck != null) {
            for (var obraListOrphanCheckObra : obraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This AreaCientifica (" + areaCientifica + ") cannot be destroyed since the Obra " + obraListOrphanCheckObra + " in its obraList field has a non-nullable idArea field.");
            }
        }

        if (areaCientifica.isPresent()) {
            utilizadorListOrphanCheck = areaCientifica.get().getUtilizadorList();
        }
        if (utilizadorListOrphanCheck != null) {
            for (var utilizadorListOrphanCheckUtilizador : utilizadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This AreaCientifica (" + areaCientifica + ") cannot be destroyed since the Utilizador " + utilizadorListOrphanCheckUtilizador + " in its utilizadorList field has a non-nullable idArea field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        areaCientificaRepository.deleteById(id);

    }

    /**
     *
     * @param descricao
     * @return
     */
    public AreaCientifica findByDescricao(String descricao) {
        return areaCientificaRepository.findByDescricao(descricao);
    }


}
