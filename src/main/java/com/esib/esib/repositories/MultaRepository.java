package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Multa;

public interface MultaRepository extends JpaRepository <Multa, Long> {
    
}
