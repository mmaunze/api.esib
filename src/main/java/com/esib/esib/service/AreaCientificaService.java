package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.repository.AreaCientificaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class AreaCientificaService {

    private final AreaCientificaRepository areaCientificaRepository;

    // CRUD methods
    
    public Optional<AreaCientifica> findById(@NonNull Long id) {
        return areaCientificaRepository.findById(id);
    }
    @Transactional
    public AreaCientifica create(AreaCientifica areaCientifica) {

        findById(areaCientifica.getId());

        return areaCientificaRepository.save(areaCientifica);
    }

   

    public List<AreaCientifica> findAll() {
        return areaCientificaRepository.findAll();
    }

    @Transactional
    public AreaCientifica update(Long id, AreaCientifica areaCientifica) {
        AreaCientifica newAreaCientifica = new AreaCientifica();
        areaCientifica.setId(id);
        newAreaCientifica.setId(id);
        newAreaCientifica.setDescricao(areaCientifica.getDescricao());
        return areaCientificaRepository.save(newAreaCientifica);
    }

    @Transactional
    public void delete(Long id) {
        areaCientificaRepository.deleteById(id);
    }

    public AreaCientifica findByDescricao(String descricao) {
        return areaCientificaRepository.findByDescricao(descricao);
    }

}
