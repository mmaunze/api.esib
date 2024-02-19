package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Emprestimo;

@Repository

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(value = "SELECT e from Emprestimo e where e.idBibliotecario.idBibliotecario =: idBibliotecario")
    List<Emprestimo> findByBibliotecario(@Param("IdBibliotecario") Long idBibliotecario);

    @Query(value = "SELECT e from Emprestimo e where e.idUtilizador.idUtilizador =: idUtilizador")
    List<Emprestimo> findByUtilizador(@Param("IdBibliotecario") Long idBibliotecario);

    @Query(value = "SELECT e from Emprestimo e where e.idUtilizador.idUtilizador =: idUtilizador")
    List<Emprestimo> findByObra(@Param("idUtilizador") Long idUtilizador);

    @Query(value = "SELECT e from Emprestimo e where e.idEstado.descricao =: descricao")
    List<Emprestimo> findByEstado(@Param("descricao") String descricao);

}
