package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Multa;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {
    @Query(value = "SELECT m from Multa m where m.idEmprestimo.idEmprestimo =:idEmprestimo")
    List<Multa> findByEmprestimo(@Param("idEmprestimo") Long idEmprestimo);

    @Query(value = "SELECT m from Multa m where m.idEstado =:idEstado")
    List<Multa> findByEstado(@Param("idEstado") Long idEstado);

}
