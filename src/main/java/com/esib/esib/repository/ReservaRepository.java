package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "SELECT r from Reserva r where r.utilizador.id =: id")
    List<Reserva> findByUtilizador(@Param("id") Long id);

    @Query(value = "SELECT r from Reserva r where r.utilizador.id =: id")
    List<Reserva> findByObra(@Param("id") Long id);

    @Query(value = "SELECT r from Reserva r where r.estado.descricao =: descricao")
    List<Reserva> findByEstado(@Param("descricao") String descricao);

    @Query(value = "SELECT r from Reserva r where r.obra.titulo =: titulo")
    List<Reserva> findByTitulo(@Param("titulo") String titulo);

    @Query(value = "SELECT r from Reserva r where r.obra.idioma.descricao =: descricao")
    List<Reserva> findByIdioma(@Param("descricao") String descricao);

    @Query(value = "SELECT r from Reserva r where r.obra.areaCientifica.descricao =: descricao")
    List<Reserva> findByAcientifica(@Param("descricao") String descricao);

}
