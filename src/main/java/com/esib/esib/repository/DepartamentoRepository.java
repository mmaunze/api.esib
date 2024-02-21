package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query(value = "SELECT d from Departamento d where d.descricao = :descricao")
    Departamento findByDescricao(@Param("descricao") String descricao);

}
