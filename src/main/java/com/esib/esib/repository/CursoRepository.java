package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Curso;

public interface CursoRepository  extends JpaRepository <Curso, Long> {
    
}