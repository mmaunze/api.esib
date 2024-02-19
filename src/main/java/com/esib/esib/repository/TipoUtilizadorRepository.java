package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.TipoUtilizador;

@Repository
public interface TipoUtilizadorRepository extends JpaRepository<TipoUtilizador, Long> {

    @Query(value = "SELECT tu from TipoUtilizador tu where tu.descricao =:descricao")
    TipoUtilizador findByDescricao(@Param("descricao") String descricao);

}
