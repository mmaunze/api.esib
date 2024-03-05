package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Departamento;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT d from Departamento d where d.sigla = :descricao")
    Departamento findByDescricao(@Param("descricao") String descricao);

}
