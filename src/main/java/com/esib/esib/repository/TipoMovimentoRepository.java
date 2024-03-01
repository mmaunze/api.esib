package com.esib.esib.repository;

import com.esib.esib.modelo.TipoMovimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface TipoMovimentoRepository extends JpaRepository<TipoMovimento, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT tm from TipoMovimento tm where tm.descricao =:descricao")
    TipoMovimento findByDescricao(@Param("descricao") String descricao);

}
