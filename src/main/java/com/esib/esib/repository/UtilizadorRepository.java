package com.esib.esib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Utilizador;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    @Query(value = "SELECT u from Utilizador u where u.contacto =: contacto")
    Optional<Utilizador> findByContacto(@Param("contacto") String contacto);

    @Query(value = "SELECT u from Utilizador u where u.email =: email")
    Optional<Utilizador> findByEmail(@Param("email") String email);

    @Query(value = "SELECT u from Utilizador u where u.username =: username")
    Optional<Utilizador> findByUsername(@Param("username") String username);

}