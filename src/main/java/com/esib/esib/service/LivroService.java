package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Livro;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.LivroRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class LivroService {

    private final LivroRepository livroRepository;

    // CRUD methods

    @Transactional
    public Livro create(Livro livro) {
        // Verifique se a obra associada existe
        Obra obra = livro.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada ao livro não informada");
        }

        /*
         * // Verifique se o ISBN é único
         * if (livroRepository.existsByIsbn(livro.getIsbn())) {
         * throw new RuntimeException("Livro com ISBN já existente");
         * }
         */
        return livroRepository.save(livro);
    }
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> findLivroPorIsbn(String isbn) {
        return livroRepository.findByIsbn(isbn);
    }

    @Transactional
    public Livro update(Livro livro) {
        return livroRepository.save(livro);
    }

    @Transactional
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    // Method related to relationship

    public Obra findObraPorLivro(Livro livro) {
        return livro.getObra();
    }

    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> findByIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);

    }

    public List<Livro> findByAreaCientifica(String areacientifica) {
        return livroRepository.findByAreaCientifica(areacientifica);
    }

    public List<Livro> findByVolume(Long volume) {
        return livroRepository.findByVolume(volume);
    }

    public List<Livro> findByEditora(String editora) {

        return livroRepository.findByEditora(editora);
    }

    public List<Livro> findByEdicao(Integer numero) {
        return livroRepository.findByEdicao(numero);
    }

}
