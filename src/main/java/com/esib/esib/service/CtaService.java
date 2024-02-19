package com.esib.esib.service;

import com.esib.esib.modelo.Cta;
import com.esib.esib.repository.CtaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtaService {

    @Autowired
    private CtaRepository ctaRepository;

    // CRUD methods

    @Transactional
    public Cta criarCta(Cta cta) {
        return ctaRepository.save(cta);
    }

    public Optional<Cta> buscarCtaPorId(Long id) {
        return ctaRepository.findById(id);
    }

    public List<Cta> buscarTodasCtas() {
        return ctaRepository.findAll();
    }

    @Transactional
    public Cta atualizarCta(Cta cta) {
        return ctaRepository.save(cta);
    }

    @Transactional
    public void excluirCta(Long id) {
        ctaRepository.deleteById(id);
    }

}
