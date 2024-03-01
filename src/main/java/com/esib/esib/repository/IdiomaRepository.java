package com.esib.esib.repository;

import com.esib.esib.modelo.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

}
