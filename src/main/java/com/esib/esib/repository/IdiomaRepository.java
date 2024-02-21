package com.esib.esib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esib.esib.modelo.Idioma;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

}
