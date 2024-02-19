package com.esib.esib.repository;

import com.esib.esib.modelo.Livro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface LivroRepository extends JpaRepository <Livro, Long>{

    @Query(" SELECT l from Livro l where l.isbn =: isbn")
    Optional<Livro> findByIsbn(@Param("isbn") String isbn);
    
}
