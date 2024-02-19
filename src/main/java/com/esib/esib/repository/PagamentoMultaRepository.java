package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.PagamentoMulta;

@Repository
public interface PagamentoMultaRepository extends JpaRepository<PagamentoMulta, Long> {

    @Query(value = "SELECT pm from PagamentoMulta pm where pm.idMulta.idMulta =:idMulta")
    List<PagamentoMulta> findByMulta(@Param("idMulta") Long idMulta);

    @Query(value = "SELECT pm from PagamentoMulta pm where pm.idBibliotecario.idBibliotecario =:idBibliotecario")
    List<PagamentoMulta> findByBibliotecario(@Param("idBibliotecario") Long idBibliotecario);

}
