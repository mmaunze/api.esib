package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.TipoObra;

@Repository
public interface TipoObraRepository extends JpaRepository<TipoObra, Long> {

    @Query(value = "SELECT to from TipoObra to where to.descricao =:descricao")
    TipoObra findByDescricao(@Param("descricao") String descricao);

}
