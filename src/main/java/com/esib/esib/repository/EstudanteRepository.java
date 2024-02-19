package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
    @Query(value = "SELECT e from Estudante e where e.idCurso.sigla =: sigla")
    List<Estudante> findByCurso(@Param("sigla") String sigla);

    @Query(value = "SELECT e from Estudante e where e.nivel =: nivel")
    List<Estudante> findByNivel(@Param ("nivel") int nivel);
}
