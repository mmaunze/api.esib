package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Monografia;

@Repository
public interface MonografiaRepository extends JpaRepository<Monografia, Long> {

    @Query(value = "SELECT m from Monografia m where m.curso.descricao =: descricao")
    List<Monografia> findByCurso(@Param("descricao") String descricao);

    @Query(value = "SELECT m from Monografia m where m.faculdade.descricao =: descricao")
    List<Monografia> findByFaculdade(@Param("descricao") String descricao);

    @Query(value = "SELECT m from Monografia m where m.obra.titulo =:titulo")
    List<Monografia> findByTitulo(@Param("titulo") String titulo);

    @Query(value = "SELECT m from Monografia m where m.obra.areaCientifica.descricao =: descricao")
    List<Monografia> findByAreaCientifica(@Param("descricao") String descricao);

    @Query(value = "SELECT m from Monografia m where m.obra.idioma.descricao =: descricao")
    List<Monografia> findByIdioma(@Param("descricao") String descricao);

    @Query(value = "SELECT m from Monografia m where m.supervisor =: supervisor")
    List<Monografia> findBySupervisor(@Param("supervisor") String supervisor);

}
