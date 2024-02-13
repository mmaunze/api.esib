package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Devolucao;

public interface DevolucaoRepository extends JpaRepository <Devolucao, Long> {
    
}
