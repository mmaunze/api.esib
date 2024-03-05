package com.esib.esib.service;

import com.esib.esib.model.Idioma;
import com.esib.esib.model.Obra;
import com.esib.esib.repository.IdiomaRepository;
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
public class IdiomaService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(IdiomaService.class.getName());

    /**
     *
     */
    private final IdiomaRepository idiomaRepository;

    // CRUD methods
    /**
     *
     * @param idioma
     * @return
     */
    @Transactional
    public Idioma create(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Idioma> findById(Long id) {
        return idiomaRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Idioma> findAll() {
        return idiomaRepository.findAll();
    }

    /**
     *
     * @param idioma
     * @return
     */
    @Transactional
    public Idioma update(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        idiomaRepository.deleteById(id);
    }

    /*
     * @Transactional
     * public void excluirIdioma(Long id) {
     * var idioma = findIdiomaById(id);
     * // Verifique se o idioma possui obras relacionadas antes de excluir
     * List<Obra> obras = idioma.getObraList();
     * 
     * if (!obras.isEmpty()) {
     * throw new
     * RuntimeException("Idioma possui obras relacionadas e não pode ser excluído");
     * }
     * 
     * 
     * }
     */
    // Method related to relationships
    /**
     *
     * @param idioma
     * @return
     */
    public List<Obra> findObrasByIdioma(Idioma idioma) {
        return idioma.getObraList();
    }

    /**
     *
     * @param idioma
     * @return
     */
    public Idioma findByDescricao(String idioma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
