package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.repository.AreaCientificaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaCientificaService {

    @Autowired
    private AreaCientificaRepository areaCientificaRepository;

    // CRUD methods

    @Transactional
    public AreaCientifica create(AreaCientifica areaCientifica) {
        return areaCientificaRepository.save(areaCientifica);
    }

    public Optional<AreaCientifica> findById(Long id) {
        return areaCientificaRepository.findById(id);
    }

    public List<AreaCientifica> findAll() {
        return areaCientificaRepository.findAll();
    }

    @Transactional
    public AreaCientifica update(AreaCientifica areaCientifica) {
        return areaCientificaRepository.save(areaCientifica);
    }

    @Transactional
    public void delete(Long id) {
        areaCientificaRepository.deleteById(id);
    }

}
