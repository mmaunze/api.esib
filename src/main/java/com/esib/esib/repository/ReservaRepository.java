package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "SELECT r from Reserva r where r.idObra.idObra =: idObra")
    List<Reserva> findByObra(@Param("idObra") Long idObra);

    @Query(value = "SELECT r from Reserva r where r.idUtilizador.idUtilizador =: idUtilizador")
    List<Reserva> findByUtilizador(@Param("idUtilizador") Long idUtilizador);

}
