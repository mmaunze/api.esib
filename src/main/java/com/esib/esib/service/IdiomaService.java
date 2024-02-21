package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Idioma;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.IdiomaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class IdiomaService {

    private final IdiomaRepository idiomaRepository;

    // CRUD methods

    @Transactional
    public Idioma create(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    public Optional<Idioma> findById(Long id) {
        return idiomaRepository.findById(id);
    }

    public List<Idioma> findAll() {
        return idiomaRepository.findAll();
    }

    @Transactional
    public Idioma update(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

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

    public List<Obra> findObrasByIdioma(Idioma idioma) {
        return idioma.getObraList();
    }

    public Idioma findByDescricao(String idioma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
