package com.esib.esib.repository;

import com.esib.esib.modelo.Faculdade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FaculdadeRepository extends JpaRepository <Faculdade, Long> {
    
}
