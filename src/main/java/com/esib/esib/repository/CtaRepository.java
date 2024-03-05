package com.esib.esib.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Cta;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface CtaRepository extends JpaRepository<Cta, Long> {

    /**
     *
     * @param contacto
     * @return
     */
    @Query(value = " SELECT c from Cta c where c.utilizador.contacto =:contacto")
    Optional<Cta> findByContacto(@Param("contacto") String contacto);

    /**
     *
     * @param username
     * @return
     */
    @Query(value = "  SELECT c from Cta c where c.utilizador.username =:username")
    Optional<Cta> findByUsername(@Param("username") String username);

    /**
     *
     * @param email
     * @return
     */
    @Query(value = "  SELECT c from Cta c where c.utilizador.email =:email")
    Optional<Cta> findByEmail(@Param("email") String email);

}