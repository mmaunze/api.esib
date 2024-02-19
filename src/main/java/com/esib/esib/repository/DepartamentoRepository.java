package com.esib.esib.repository;

import com.esib.esib.modelo.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DepartamentoRepository extends JpaRepository <Departamento, Long> {
    
}
