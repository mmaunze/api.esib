package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Livro;

public interface LivroRepository extends JpaRepository <Livro, Long>{
    
}
