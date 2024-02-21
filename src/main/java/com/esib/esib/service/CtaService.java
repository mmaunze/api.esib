package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Cta;
import com.esib.esib.repository.CtaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class CtaService {

    private final CtaRepository ctaRepository;

    // CRUD methods

    @Transactional
    public Cta create(Cta cta) {
        return ctaRepository.save(cta);
    }

    public Optional<Cta> findById(Long id) {
        return ctaRepository.findById(id);
    }

    public List<Cta> findAll() {
        return ctaRepository.findAll();
    }

    @Transactional
    public Cta update(Cta cta) {
        return ctaRepository.save(cta);
    }

    @Transactional
    public void delete(Long id) {
        ctaRepository.deleteById(id);
    }

    public Optional<Cta> findCtaPorUsername(String username) {
        return ctaRepository.findByUsername(username);
    }

    public Optional<Cta> findByContacto(String contacto) {

        return ctaRepository.findByContacto(contacto);
    }

    public Optional<Cta> findByEmail(String email) {
        return ctaRepository.findByEmail(email);
    }

}
