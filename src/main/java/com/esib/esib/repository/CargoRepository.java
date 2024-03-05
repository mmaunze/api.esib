package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Cargo;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface CargoRepository  extends JpaRepository<Cargo, Long>{
    
}
