package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.RevistaPeriodica;

public interface RevistaPeriodicaRepository extends JpaRepository <RevistaPeriodica, Long> {
    
}
