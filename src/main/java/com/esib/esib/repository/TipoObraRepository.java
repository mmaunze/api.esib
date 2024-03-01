package com.esib.esib.repository;

import com.esib.esib.modelo.TipoObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface TipoObraRepository extends JpaRepository<TipoObra, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT to from TipoObra to where to.descricao =:descricao")
    TipoObra findByDescricao(@Param("descricao") String descricao);

}
