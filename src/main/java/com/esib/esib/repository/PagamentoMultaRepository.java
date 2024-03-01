package com.esib.esib.repository;

import com.esib.esib.modelo.PagamentoMulta;
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
public interface PagamentoMultaRepository extends JpaRepository<PagamentoMulta, Long> {

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT pm from PagamentoMulta pm where pm.multa.id =:id")
    List<PagamentoMulta> findByMulta(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT pm from PagamentoMulta pm where pm.bibliotecario.id =:id")
    List<PagamentoMulta> findByBibliotecario(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT pm from PagamentoMulta pm where pm.multa.emprestimo.utilizador.id =:id")
    List<PagamentoMulta> findByUtilizador(@Param("id") Long id);

}
