package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Emprestimo;

public interface EmprestimoRepository extends JpaRepository <Emprestimo, Long>{
    
}
