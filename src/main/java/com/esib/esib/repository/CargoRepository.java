package com.esib.esib.repository;

import com.esib.esib.modelo.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface CargoRepository  extends JpaRepository<Cargo, Long>{
    
}
