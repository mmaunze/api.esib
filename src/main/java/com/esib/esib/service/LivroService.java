package com.esib.esib.service;

import com.esib.esib.modelo.Livro;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.LivroRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    // CRUD methods

    @Transactional
    public Livro criarLivro(Livro livro) {
        // Verifique se a obra associada existe
        Obra obra = livro.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada ao livro não informada");
        }
        
/*
        // Verifique se o ISBN é único
        if (livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new RuntimeException("Livro com ISBN já existente");
        }
*/
        return livroRepository.save(livro);
    }

    public Optional<Livro> buscarLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> buscarLivroPorIsbn(String isbn) {
        return livroRepository.findByIsbn(isbn);
    }

    @Transactional
    public Livro atualizarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Transactional
    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }

    // Method related to relationship

    public Obra buscarObraPorLivro(Livro livro) {
        return livro.getObra();
    }
}
