package com.esib.esib.service;

import com.esib.esib.modelo.Idioma;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.IdiomaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    // CRUD methods

    @Transactional
    public Idioma criarIdioma(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    public Optional<Idioma> buscarIdiomaPorId(Long id) {
        return idiomaRepository.findById(id);
    }

    public List<Idioma> buscarTodosIdiomas() {
        return idiomaRepository.findAll();
    }

    @Transactional
    public Idioma atualizarIdioma(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }
/* 
    @Transactional
    public void excluirIdioma(Long id) {
        var idioma = buscarIdiomaPorId(id);
        // Verifique se o idioma possui obras relacionadas antes de excluir
        List<Obra> obras = idioma.getObraList();

        if (!obras.isEmpty()) {
            throw new RuntimeException("Idioma possui obras relacionadas e não pode ser excluído");
        }

        idiomaRepository.deleteById(id);
    }
*/
    // Method related to relationships

    public List<Obra> buscarObrasPorIdioma(Idioma idioma) {
        return idioma.getObraList();
    }

}
