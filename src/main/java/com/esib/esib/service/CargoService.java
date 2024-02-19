package com.esib.esib.service;

import com.esib.esib.modelo.Cargo;
import com.esib.esib.repository.CargoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    // CRUD methods

    @Transactional
    public Cargo criarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public Optional<Cargo> buscarCargoPorId(Long id) {
        return cargoRepository.findById(id);
    }

    public List<Cargo> buscarTodosCargos() {
        return cargoRepository.findAll();
    }

    @Transactional
    public Cargo atualizarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Transactional
    public void excluirCargo(Long id) {
        cargoRepository.deleteById(id);
    }

}
