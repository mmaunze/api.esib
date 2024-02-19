package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Emprestimo;

@Repository

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(value = "SELECT e from Emprestimo e where e.bibliotecario.id =: id")
    List<Emprestimo> findByBibliotecario(@Param("bibliotecario") Long id);

    @Query(value = "SELECT e from Emprestimo e where e.utilizador.id =: id")
    List<Emprestimo> findByUtilizador(@Param("id") Long id);

    @Query(value = "SELECT e from Emprestimo e where e.utilizador.id =: id")
    List<Emprestimo> findByObra(@Param("id") Long id);

    @Query(value = "SELECT e from Emprestimo e where e.estado.descricao =: descricao")
    List<Emprestimo> findByEstado(@Param("descricao") String descricao);

}
