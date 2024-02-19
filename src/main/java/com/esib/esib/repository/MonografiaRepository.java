package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Monografia;

@Repository
public interface MonografiaRepository extends JpaRepository<Monografia, Long> {

    @Query(value = "SELECT m from Monografia  m")
    List<Monografia> findByCurso();

    @Query(value = "SELECT m from Monografia m")
    List<Monografia> findByFaculdade();

    /*
     * @Query(value = "SELECT m from Monografia where m.idCurso.sigla =: sigla")
     * List<Monografia> findByCurso(@Param("sigla") String sigla);
     * 
     * @Query(value = "SELECT m from Monografia where m.idFaculdade.sigla =: sigla")
     * List<Monografia> findByFaculdade(@Param("sigla") String sigla);
     */

}
