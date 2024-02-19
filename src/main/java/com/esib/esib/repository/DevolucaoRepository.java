package com.esib.esib.repository;

import com.esib.esib.modelo.Devolucao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    @Query(value = "SELECT d from Devolucao d where d.bibliotecario.id = :id ")
    List<Devolucao> findByBibliotecario(@Param("id") Long id);

}
