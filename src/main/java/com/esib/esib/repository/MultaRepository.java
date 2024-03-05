package com.esib.esib.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Multa;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT m from Multa m where m.emprestimo.id =:id")
    List<Multa> findByEmprestimo(@Param("id") Long id);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT m from Multa m where m.estado.descricao =:descricao")
    List<Multa> findByEstado(@Param("descricao") String descricao);

}
