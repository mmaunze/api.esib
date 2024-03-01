package com.esib.esib.repository;

import com.esib.esib.modelo.Devolucao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT d from Devolucao d where d.bibliotecario.id = :id ")
    List<Devolucao> findByBibliotecario(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT d from Devolucao d where d.emprestimo.utilizador.id =: id")
    List<Devolucao> findByUtilizador(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT d from Devolucao d where d.emprestimo.obra.id =: id")
    List<Devolucao> findByObra(@Param("id") Long id);

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT d from Devolucao d where d.emprestimo.obra.titulo =: titulo")
    List<Devolucao> findByTitulo(@Param("titulo") String titulo);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT d from Devolucao d where d.emprestimo.obra.idioma.descricao =: descricao")
    List<Devolucao> findByIdioma(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT d from Devolucao d where d.emprestimo.obra.areaCientifica.descricao =: descricao")
    List<Devolucao> findByAreaCientifica(@Param("descricao") String descricao);

}
