package com.esib.esib.repository;

import com.esib.esib.modelo.Estudante;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT e from Estudante e where e.curso.descricao =: descricao")
    Optional<Estudante> findByCurso(@Param("descricao") String descricao);

    /**
     *
     * @param nivel
     * @return
     */
    @Query(value = "SELECT e from Estudante e where e.nivel =: nivel")
    List<Estudante> findByNivel(@Param("nivel") int nivel);

    /**
     *
     * @param contacto
     * @return
     */
    @Query(value = "SELECT e from Estudante e where e.utilizador.contacto =:contacto")
    Optional<Estudante> findByContacto(@Param("contacto") String contacto);

    /**
     *
     * @param username
     * @return
     */
    @Query(value = "SELECT e from Estudante e where e.utilizador.username =:username")
    Optional<Estudante> findByUsername(@Param("username") String username);

    /**
     *
     * @param email
     * @return
     */
    @Query(value = "SELECT e from Estudante e where e.utilizador.email =:email")
    Optional<Estudante> findByEmail(@Param("email") String email);
}
