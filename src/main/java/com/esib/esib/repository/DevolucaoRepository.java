package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Devolucao;

public interface DevolucaoRepository extends JpaRepository <Devolucao, Long> {
    
}
