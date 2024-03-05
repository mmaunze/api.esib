package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Faculdade;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface FaculdadeRepository extends JpaRepository<Faculdade, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT f from Faculdade f where f.descricao =:descricao")
    Faculdade findByDescricao(@Param("descricao") String descricao);

}
