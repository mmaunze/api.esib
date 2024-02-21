package com.esib.esib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    @Query(value = "SELECT o from Obra o where o.autor1 =:autor1 and o.autor2 =:autor2 and o.autor3 =:autor3")
    List<Obra> findByAutor1ContainingIgnoreCaseOrAutor2ContainingIgnoreCaseOrAutor3ContainingIgnoreCase(
            @Param("autor1") String autor1,
            @Param("autor2") String autor2,
            @Param("autor3") String autor3);

    @Query(value = "SELECT o from Obra o where o.titulo =:titulo")
    List<Obra> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    @Query(value = "SELECT o from Obra o where o.areaCientifica.descricao =: areaCientifica")
    List<Obra> findByAreaCientifica(@Param("areaCientifica") String areaCientifica);

    @Query(value = "SELECT o from Obra o where o.idioma.descricao =: descricao")
    List<Obra> findByIdioma(@Param("idioma") String descricao);

    @Query(value = "SELECT o from Obra o where o.titulo =: titulo")
    List<Obra> findByTitulo(@Param("titulo") String titulo);

}
