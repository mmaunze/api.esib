package com.esib.esib.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT l from Livro l where l.obra.titulo =:titulo")
    List<Livro> findByTitulo(@Param("titulo") String titulo);

    @Query(value = "SELECT l from Livro l where l.obra.areaCientifica.descricao =: descricao")
    List<Livro> findByAreaCientifica(@Param("descricao") String descricao);

    @Query(value = "SELECT l from Livro l where l.obra.idioma.descricao =: descricao")
    List<Livro> findByIdioma(@Param("descricao") String descricao);

    @Query(value = "SELECT l from Livro l where l.editora =: editora")
    List<Livro> findByEditora(@Param("editora") String editora);

    @Query(value = "SELECT l from Livro l where l.volume =: volume")
    List<Livro> findByVolume(@Param("volume") Long volume);

    @Query(value = "SELECT l from Livro l where l.edicao =: edicao")
    List<Livro> findByEdicao(@Param("numero") Integer edicao);

    @Query(" SELECT l from Livro l where l.isbn =: isbn")
    Optional<Livro> findByIsbn(@Param("isbn") String isbn);

}
