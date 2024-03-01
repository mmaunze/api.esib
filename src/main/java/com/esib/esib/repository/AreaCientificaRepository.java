package com.esib.esib.repository;

import com.esib.esib.modelo.AreaCientifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface AreaCientificaRepository extends JpaRepository<AreaCientifica, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT a from AreaCientifica a where a.descricao = :descricao")
    AreaCientifica findByDescricao(@Param("descricao") String descricao);

}
