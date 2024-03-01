package com.esib.esib.service;

import com.esib.esib.modelo.Livro;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.LivroRepository;
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
public class LivroService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(LivroService.class.getName());

    /**
     *
     */
    private final LivroRepository livroRepository;

    // CRUD methods
    /**
     *
     * @param livro
     * @return
     */
    @Transactional
    public Livro create(Livro livro) {

        Obra obra = livro.getObra();
        if (obra == null) {
            throw new IllegalArgumentException("Obra associada ao livro não informada");
        }

        // Verifique se o ISBN é único
        if (livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new IllegalArgumentException("Livro com ISBN já existente");
        }

        return livroRepository.save(livro);
    }

    /**
     *
     * @return
     */
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    /**
     *
     * @param isbn
     * @return
     */
    public Optional<Livro> findLivroPorIsbn(String isbn) {
        return livroRepository.findByIsbn(isbn);
    }

    /**
     *
     * @param livro
     * @return
     */
    @Transactional
    public Livro update(Livro livro) {
        return livroRepository.save(livro);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    // Method related to relationship
    /**
     *
     * @param livro
     * @return
     */
    public Obra findObraPorLivro(Livro livro) {
        return livro.getObra();
    }

    /**
     *
     * @param titulo
     * @return
     */
    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    /**
     *
     * @param idioma
     * @return
     */
    public List<Livro> findByIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);

    }

    /**
     *
     * @param areacientifica
     * @return
     */
    public List<Livro> findByAreaCientifica(String areacientifica) {
        return livroRepository.findByAreaCientifica(areacientifica);
    }

    /**
     *
     * @param volume
     * @return
     */
    public List<Livro> findByVolume(Long volume) {
        return livroRepository.findByVolume(volume);
    }

    /**
     *
     * @param editora
     * @return
     */
    public List<Livro> findByEditora(String editora) {

        return livroRepository.findByEditora(editora);
    }

    /**
     *
     * @param numero
     * @return
     */
    public List<Livro> findByEdicao(Integer numero) {
        return livroRepository.findByEdicao(numero);
    }

}
