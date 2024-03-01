package com.esib.esib.repository;

import com.esib.esib.modelo.Obra;
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
public interface ObraRepository extends JpaRepository<Obra, Long> {

    /**
     *
     * @param autor1
     * @param autor2
     * @param autor3
     * @return
     */
    @Query(value = "SELECT o from Obra o where o.autor1 =:autor1 and o.autor2 =:autor2 and o.autor3 =:autor3")
    List<Obra> findByAutor1ContainingIgnoreCaseOrAutor2ContainingIgnoreCaseOrAutor3ContainingIgnoreCase(
            @Param("autor1") String autor1,
            @Param("autor2") String autor2,
            @Param("autor3") String autor3);

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT o from Obra o where o.titulo =:titulo")
    List<Obra> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    /**
     *
     * @param areaCientifica
     * @return
     */
    @Query(value = "SELECT o from Obra o where o.areaCientifica.descricao =: areaCientifica")
    List<Obra> findByAreaCientifica(@Param("areaCientifica") String areaCientifica);

    /**
     *
     * @param descricao
     * @return
     */
    @Query(value = "SELECT o from Obra o where o.idioma.descricao =: descricao")
    List<Obra> findByIdioma(@Param("idioma") String descricao);

    /**
     *
     * @param titulo
     * @return
     */
    @Query(value = "SELECT o from Obra o where o.titulo =: titulo")
    List<Obra> findByTitulo(@Param("titulo") String titulo);

}
