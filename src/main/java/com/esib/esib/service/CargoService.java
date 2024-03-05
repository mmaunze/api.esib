package com.esib.esib.service;

import com.esib.esib.model.Cargo;
import com.esib.esib.repository.CargoRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Meldo Maunze
 */
@Service
@RequiredArgsConstructor
@Data
public class CargoService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(CargoService.class.getName());

    /**
     *
     */
    private final CargoRepository cargoRepository;

    // CRUD methods
    /**
     *
     * @param cargo
     * @return
     */
    @Transactional
    public Cargo create(Cargo cargo) {
        if (cargo.getId() != null) {
            cargo.setId(null);
        }
        return cargoRepository.save(cargo);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Cargo> findById(Long id) {
        return cargoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    /**
     *
     * @param cargo
     * @return
     */
    @Transactional
    public Cargo update(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        cargoRepository.deleteById(id);
    }

    /**
     *
     * @param cargo
     * @return
     */
    public Cargo findByDescricao(String cargo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
