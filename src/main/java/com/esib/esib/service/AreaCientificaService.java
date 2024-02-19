package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.repository.AreaCientificaRepository;

@Service
public class AreaCientificaService {

    @Autowired
    private AreaCientificaRepository areaCientificaRepository;

    // CRUD methods

    @Transactional
    public AreaCientifica criarAreaCientifica(AreaCientifica areaCientifica) {
        return areaCientificaRepository.save(areaCientifica);
    }

    public Optional<AreaCientifica> buscarAreaCientificaPorId(Long id) {
        return areaCientificaRepository.findById(id);
    }

    public List<AreaCientifica> buscarTodasAreaCientificas() {
        return areaCientificaRepository.findAll();
    }

    @Transactional
    public AreaCientifica atualizarAreaCientifica(AreaCientifica areaCientifica) {
        return areaCientificaRepository.save(areaCientifica);
    }

    @Transactional
    public void excluirAreaCientifica(Long id) {
        areaCientificaRepository.deleteById(id);
    }

}
