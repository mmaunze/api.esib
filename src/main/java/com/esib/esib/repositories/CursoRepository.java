package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Curso;

public interface CursoRepository  extends JpaRepository <Curso, Long> {
    
}