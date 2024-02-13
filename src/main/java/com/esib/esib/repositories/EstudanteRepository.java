package com.esib.esib.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Estudante;

public interface EstudanteRepository  extends JpaRepository<Estudante,Long>{
  
}
