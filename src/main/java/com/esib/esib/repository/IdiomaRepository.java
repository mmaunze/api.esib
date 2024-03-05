package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esib.esib.model.Idioma;

/**
 *
 * @author Meldo Maunze
 */
@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

}
