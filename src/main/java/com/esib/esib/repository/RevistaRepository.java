package com.esib.esib.repository;

import com.esib.esib.modelo.Revista;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface RevistaRepository extends JpaRepository<Revista, Long> {

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.obra.titulo =:titulo")
    List<Revista> findByTitulo(@Param("titulo") String titulo);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.obra.areaCientifica.descricao =: descricao")
    List<Revista> findByAreaCientifica(@Param("descricao") String descricao);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.obra.idioma.descricao =: descricao")
    List<Revista> findByIdioma(@Param("descricao") String descricao);

    /**
     *
     * @param editora
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.editora =: editora")
    List<Revista> findByEditora(@Param("editora") String editora);

    /**
     *
     * @param nome
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.nome =: nome")
    List<Revista> findByNome(@Param("nome") String nome);

    /**
     *
     * @param volume
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.volume =: volume")
    List<Revista> findByVolume(@Param("volume") Long volume);

    /**
     *
     * @param numero
     * @return
     */
    @Query(value = "SELECT r from Revista r where r.numero =: numero")
    List<Revista> findByNumero(@Param("numero") Integer numero);

}
