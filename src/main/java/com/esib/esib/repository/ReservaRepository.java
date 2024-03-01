package com.esib.esib.repository;

import com.esib.esib.modelo.Reserva;
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
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT r from Reserva r where r.utilizador.id =: id")
    List<Reserva> findByUtilizador(@Param("id") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT r from Reserva r where r.utilizador.id =: id")
    List<Reserva> findByObra(@Param("id") Long id);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT r from Reserva r where r.estado.descricao =: descricao")
    List<Reserva> findByEstado(@Param("descricao") String descricao);

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT r from Reserva r where r.obra.titulo =: titulo")
    List<Reserva> findByTitulo(@Param("titulo") String titulo);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT r from Reserva r where r.obra.idioma.descricao =: descricao")
    List<Reserva> findByIdioma(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT r from Reserva r where r.obra.areaCientifica.descricao =: descricao")
    List<Reserva> findByAcientifica(@Param("descricao") String descricao);

}
