package com.esib.esib.repository;

import com.esib.esib.modelo.RevistaPeriodica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RevistaPeriodicaRepository extends JpaRepository <RevistaPeriodica, Long> {
    
}
