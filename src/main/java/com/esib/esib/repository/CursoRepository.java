package com.esib.esib.repository;

import com.esib.esib.modelo.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query(value = "SELECT c from Curso c where c.faculdade.descricao =: descricao")
    List<Curso> findByFaculdade(@Param("descricao") String descricao);

}