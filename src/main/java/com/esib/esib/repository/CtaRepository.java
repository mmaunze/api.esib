package com.esib.esib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Cta;

@Repository
public interface CtaRepository extends JpaRepository<Cta, Long> {

    @Query(value = " SELECT c from Cta c where c.utilizador.contacto =:contacto")
    Optional<Cta> findByContacto(@Param("contacto") String contacto);

    @Query(value = "  SELECT c from Cta c where c.utilizador.username =:username")
    Optional<Cta> findByUsername(@Param("username") String username);

    @Query(value = "  SELECT c from Cta c where c.utilizador.email =:email")
    Optional<Cta> findByEmail(@Param("email") String email);

}