package com.esib.esib.repository;

import com.esib.esib.modelo.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = " SELECT e from Estado e where e.descricao=: descricao")
    Estado findByDescricao(@Param("descricao") String descricao);

}
