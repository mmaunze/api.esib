package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Cargo;
import com.esib.esib.repository.CargoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class CargoService {

    private final CargoRepository cargoRepository;

    // CRUD methods

    @Transactional
    public Cargo create(Cargo cargo) {
        if (cargo.getId() != null)
            cargo.setId(null);
        return cargoRepository.save(cargo);
    }

    public Optional<Cargo> findById(Long id) {
        return cargoRepository.findById(id);
    }

    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Transactional
    public Cargo update(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Transactional
    public void delete(Long id) {
        cargoRepository.deleteById(id);
    }

    public Cargo findByDescricao(String cargo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
