package com.esib.esib.service;

import com.esib.esib.modelo.Departamento;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.DepartamentoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // CRUD methods

    @Transactional
    public Departamento criarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Optional<Departamento> buscarDepartamentoPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    public List<Departamento> buscarTodosDepartamentos() {
        return departamentoRepository.findAll();
    }

    @Transactional
    public Departamento atualizarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Transactional
    public void excluirDepartamento(Long id) {
        departamentoRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Utilizador> buscarUtilizadoresPorDepartamento(Departamento departamento) {
        return departamento.getUtilizadorList();
    }

}
