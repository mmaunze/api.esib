package com.esib.esib.service;

import com.esib.esib.model.Obra;
import com.esib.esib.model.Revista;
import com.esib.esib.repository.RevistaRepository;
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
public class RevistaService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(RevistaService.class.getName());

    /**
     *
     */
    private final RevistaRepository revistaPeriodicaRepository;

    // CRUD methods
    /**
     *
     * @param revistaPeriodica
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    public Optional<Revista> findById(Long id) {
        return revistaPeriodicaRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Revista> findAll() {
        return revistaPeriodicaRepository.findAll();
    }

    /**
     *
     * @param obra
     * @return
     */
    public Revista findRevistaPeriodicaPorObra(Obra obra) {
        return obra.getRevistaPeriodica();
    }

    /**
     *
     * @param revistaPeriodica
     * @return
     */
    @Transactional
    public Revista update(Revista revistaPeriodica) {
        return revistaPeriodicaRepository.save(revistaPeriodica);
    }

    /**
     *
     * @param id
     */
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
    /**
     *
     * @param revistaPeriodica
     * @return
     */
    public Obra findObraPorRevistaPeriodica(Revista revistaPeriodica) {
        return revistaPeriodica.getObra();
    }

    /**
     *
     * @param titulo
     * @return
     */
    public List<Revista> findByTitulo(String titulo) {
        return revistaPeriodicaRepository.findByTitulo(titulo);
    }

    /**
     *
     * @param idioma
     * @return
     */
    public List<Revista> findByIdioma(String idioma) {
        return revistaPeriodicaRepository.findByIdioma(idioma);

    }

    /**
     *
     * @param areacientifica
     * @return
     */
    public List<Revista> findByAreaCientifica(String areacientifica) {

        return revistaPeriodicaRepository.findByAreaCientifica(areacientifica);

    }

    /**
     *
     * @param volume
     * @return
     */
    public List<Revista> findByVolume(Long volume) {

        return revistaPeriodicaRepository.findByVolume(volume);

    }

    /**
     *
     * @param editora
     * @return
     */
    public List<Revista> findByEditora(String editora) {

        return revistaPeriodicaRepository.findByEditora(editora);

    }

    /**
     *
     * @param nome
     * @return
     */
    public List<Revista> findByNome(String nome) {
        return revistaPeriodicaRepository.findByNome(nome);

    }

    /**
     *
     * @param numero
     * @return
     */
    public List<Revista> findByNumero(Integer numero) {
        return revistaPeriodicaRepository.findByNumero(numero);
    }

}
