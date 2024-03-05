package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.TipoUtilizador;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface TipoUtilizadorRepository extends JpaRepository<TipoUtilizador, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT tu from TipoUtilizador tu where tu.descricao =:descricao")
    TipoUtilizador findByDescricao(@Param("descricao") String descricao);

}
