package com.esib.esib.repository;

import com.esib.esib.modelo.Cta;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
@Repository
public interface CtaRepository extends JpaRepository <Cta, Long> {
    
}