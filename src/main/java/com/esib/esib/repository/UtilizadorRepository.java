package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Utilizador;

public interface UtilizadorRepository extends JpaRepository < Utilizador, Long> {
    
}