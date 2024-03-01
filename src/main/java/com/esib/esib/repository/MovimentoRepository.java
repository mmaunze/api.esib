package com.esib.esib.repository;

import com.esib.esib.modelo.Movimento;
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
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT m from Movimento m where m.utilizador.id = :id")
    List<Movimento> findByUtilizador(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT m from Movimento m where m.obra.id = :id")
    List<Movimento> findByObra(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT m from Movimento m where m.bibliotecario.id =: id")
    List<Movimento> findByBibliotecario(@Param("bibliotecario") Long id);

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT m from Movimento m where m.obra.titulo =: titulo")
    List<Movimento> findByTitulo(@Param("titulo") String titulo);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT m from Movimento m where m.obra.idioma.descricao =: descricao")
    List<Movimento> findByIdioma(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT m from Movimento m where m.obra.areaCientifica.descricao =: descricao")
    List<Movimento> findByAcientifica(@Param("descricao") String descricao);
}
