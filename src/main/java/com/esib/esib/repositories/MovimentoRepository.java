package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Movimento;

public interface MovimentoRepository extends JpaRepository <Movimento, Long>{
    
}
