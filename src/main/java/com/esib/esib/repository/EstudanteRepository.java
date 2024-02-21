package com.esib.esib.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

    @Query(value = "SELECT e from Estudante e where e.curso.descricao =: descricao")
    Optional<Estudante> findByCurso(@Param("descricao") String descricao);

    @Query(value = "SELECT e from Estudante e where e.nivel =: nivel")
    List<Estudante> findByNivel(@Param("nivel") int nivel);

    @Query(value = "SELECT e from Estudante e where e.utilizador.contacto =:contacto")
    Optional<Estudante> findByContacto(@Param("contacto") String contacto);

    @Query(value = "SELECT e from Estudante e where e.utilizador.username =:username")
    Optional<Estudante> findByUsername(@Param("username") String username);

    @Query(value = "SELECT e from Estudante e where e.utilizador.email =:email")
    Optional<Estudante> findByEmail(@Param("email") String email);
}
