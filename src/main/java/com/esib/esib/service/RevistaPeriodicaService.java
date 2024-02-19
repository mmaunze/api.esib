package com.esib.esib.service;

import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.RevistaPeriodica;
import com.esib.esib.repository.RevistaPeriodicaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevistaPeriodicaService {

    @Autowired
    private RevistaPeriodicaRepository revistaPeriodicaRepository;

    // CRUD methods

    @Transactional
    public RevistaPeriodica criarRevistaPeriodica(RevistaPeriodica revistaPeriodica) {
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

    public Optional<RevistaPeriodica> buscarRevistaPeriodicaPorId(Long id) {
        return revistaPeriodicaRepository.findById(id);
    }

    public List<RevistaPeriodica> buscarTodasRevistasPeriodicas() {
        return revistaPeriodicaRepository.findAll();
    }

    public RevistaPeriodica buscarRevistaPeriodicaPorObra(Obra obra) {
        return obra.getRevistaPeriodica();
    }

    @Transactional
    public RevistaPeriodica atualizarRevistaPeriodica(RevistaPeriodica revistaPeriodica) {
        return revistaPeriodicaRepository.save(revistaPeriodica);
    }

    @Transactional
    public void excluirRevistaPeriodica(Long id) {
        // Verifique se a revista periódica está associada a uma obra antes de excluir
        var revistaPeriodica = revistaPeriodicaRepository.findById(id).get();
        if (revistaPeriodica.getObra() != null) {
            throw new RuntimeException("Revista periódica está associada a uma obra e não pode ser excluída");
        }

        revistaPeriodicaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Obra buscarObraPorRevistaPeriodica(RevistaPeriodica revistaPeriodica) {
        return revistaPeriodica.getObra();
    }

}
