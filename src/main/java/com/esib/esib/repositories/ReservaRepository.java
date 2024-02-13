package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Reserva;

public interface ReservaRepository extends JpaRepository <Reserva, Long> {
    
}
