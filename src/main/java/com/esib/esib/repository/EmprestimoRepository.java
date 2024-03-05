package com.esib.esib.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Emprestimo;

/**
 *
 * @author Meldo Maunze
 */
@Repository

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.bibliotecario.id =: id")
    List<Emprestimo> findByBibliotecario(@Param("bibliotecario") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.utilizador.id =: id")
    List<Emprestimo> findByUtilizador(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.utilizador.id =: id")
    List<Emprestimo> findByObra(@Param("id") Long id);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.estado.descricao =: descricao")
    List<Emprestimo> findByEstado(@Param("descricao") String descricao);

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.obra.titulo =: titulo")
    List<Emprestimo> findByTitulo(@Param("titulo") String titulo);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.obra.idioma.descricao =: descricao")
    List<Emprestimo> findByIdioma(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT e from Emprestimo e where e.obra.areaCientifica.descricao =: descricao")
    List<Emprestimo> findByAcientifica(@Param("descricao") String descricao);
}
