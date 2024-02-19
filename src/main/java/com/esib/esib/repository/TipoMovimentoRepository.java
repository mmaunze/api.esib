package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.TipoMovimento;

@Repository
public interface TipoMovimentoRepository extends JpaRepository<TipoMovimento, Long> {

    @Query(value = "SELECT tm from TipoMovimento tm where tm.descricao =:descricao")
    TipoMovimento findByDescricao(@Param("descricao") String descricao);

}
