package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    @Query(value = "SELECT m from Movimento m where m.idUtilizador.idUtilizador = :idUtilizador")
    List<Movimento> findByUtilizador(@Param("idUtilizador") Long idUtilizador);

    @Query(value = "SELECT m from Movimento m where m.idObra.idObra = :idObra")
    List<Movimento> findByObra(@Param("idObra") Long idObra);
}
