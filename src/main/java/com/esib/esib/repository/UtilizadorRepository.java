package com.esib.esib.repository;

import com.esib.esib.modelo.Utilizador;
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
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    /**
     *
     * @param contacto
     * @return
     */
    @Query(value = "SELECT u from Utilizador u where u.contacto =: contacto")
    Optional<Utilizador> findByContacto(@Param("contacto") String contacto);

    /**
     *
     * @param email
     * @return
     */
    @Query(value = "SELECT u from Utilizador u where u.email =: email")
    Optional<Utilizador> findByEmail(@Param("email") String email);

    /**
     *
     * @param username
     * @return
     */
    @Query(value = "SELECT u from Utilizador u where u.username =: username")
    Optional<Utilizador> findByUsername(@Param("username") String username);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT u from Utilizador u where u.areaCientifica.descricao =: descricao")
    List<Utilizador> findByAreaCientifica(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT u from Utilizador u where u.departamento.descricao =: descricao")
    List<Utilizador> findByDepartamento(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT u from Utilizador u where u.tipoUtilizador.descricao =: descricao")
    List<Utilizador> findByTipoUtilizador(@Param("descricao") String descricao);

}