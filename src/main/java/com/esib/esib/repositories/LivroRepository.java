package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Livro;

public interface LivroRepository extends JpaRepository <Livro, Long>{
    
}
