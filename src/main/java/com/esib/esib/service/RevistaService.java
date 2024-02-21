package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Revista;
import com.esib.esib.repository.RevistaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class RevistaService {

    private final RevistaRepository revistaPeriodicaRepository;

    // CRUD methods

    @Transactional
    public Revista create(Revista revistaPeriodica) {
        // Verifique se a obra associada existe
        Obra obra = revistaPeriodica.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada à revista periódica não informada");
        }

        // Verifique se a obra já está associada a outra revista periódica
        if (obra.getRevistaPeriodica() != null) {
            throw new RuntimeException("Obra já possui uma revista periódica associada");
        }

        // Salve a revista periódica
        obra.setRevistaPeriodica(revistaPeriodica);
        revistaPeriodicaRepository.save(revistaPeriodica);

        return revistaPeriodica;
    }

    public Optional<Revista> findById(Long id) {
        return revistaPeriodicaRepository.findById(id);
    }

    public List<Revista> findAll() {
        return revistaPeriodicaRepository.findAll();
    }

    public Revista findRevistaPeriodicaPorObra(Obra obra) {
        return obra.getRevistaPeriodica();
    }

    @Transactional
    public Revista update(Revista revistaPeriodica) {
        return revistaPeriodicaRepository.save(revistaPeriodica);
    }

    @Transactional
    public void delete(Long id) {
        // Verifique se a revista periódica está associada a uma obra antes de excluir
        var revistaPeriodica = revistaPeriodicaRepository.findById(id).get();
        if (revistaPeriodica.getObra() != null) {
            throw new RuntimeException("Revista periódica está associada a uma obra e não pode ser excluída");
        }

        revistaPeriodicaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Obra findObraPorRevistaPeriodica(Revista revistaPeriodica) {
        return revistaPeriodica.getObra();
    }

    public List<Revista> findByTitulo(String titulo) {
        return revistaPeriodicaRepository.findByTitulo(titulo);
    }

    public List<Revista> findByIdioma(String idioma) {
        return revistaPeriodicaRepository.findByIdioma(idioma);

    }

    public List<Revista> findByAreaCientifica(String areacientifica) {

        return revistaPeriodicaRepository.findByAreaCientifica(areacientifica);

    }

    public List<Revista> findByVolume(Long volume) {

        return revistaPeriodicaRepository.findByVolume(volume);

    }

    public List<Revista> findByEditora(String editora) {

        return revistaPeriodicaRepository.findByEditora(editora);

    }

    public List<Revista> findByNome(String nome) {
        return revistaPeriodicaRepository.findByNome(nome);

    }

    public List<Revista> findByNumero(Integer numero) {
        return revistaPeriodicaRepository.findByNumero(numero);
    }

}
