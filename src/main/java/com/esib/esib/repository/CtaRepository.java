package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Cta;

public interface CtaRepository extends JpaRepository <Cta, Long> {
    
}