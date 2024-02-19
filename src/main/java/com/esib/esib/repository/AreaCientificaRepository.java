package com.esib.esib.repository;

import com.esib.esib.modelo.AreaCientifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AreaCientificaRepository  extends JpaRepository<AreaCientifica, Long>{
    
}
