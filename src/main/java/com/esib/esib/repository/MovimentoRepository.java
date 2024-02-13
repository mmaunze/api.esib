package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Movimento;

public interface MovimentoRepository extends JpaRepository <Movimento, Long>{
    
}
