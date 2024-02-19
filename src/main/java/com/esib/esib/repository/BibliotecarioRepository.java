package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Bibliotecario;

@Repository
public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long> {

    @Query(value = " SELECT b from Bibliotecario b where b.utilizador.nome =:nome")
    List<Bibliotecario> findByIDUtilizadorNome(@Param("nome") String nome);

    @Query(value = " SELECT b from Bibliotecario b where b.idFaculdade.descricao =:descricao")
    List<Bibliotecario> findByIdFaculdade (@Param("descricao") String descricao);

}
