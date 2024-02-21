package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.DepartamentoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    // CRUD methods

    @Transactional
    public Departamento create(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    @Transactional
    public Departamento update(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Transactional
    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Utilizador> findUtilizadoresPorDepartamento(Departamento departamento) {
        return departamento.getUtilizadorList();
    }

    public Departamento findByDescricao(String departamento) {
        return departamentoRepository.findByDescricao(departamento);
    }

}
