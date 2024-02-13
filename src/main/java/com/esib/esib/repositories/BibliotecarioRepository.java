package com.esib.esib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esib.esib.modelo.Bibliotecario;

public interface BibliotecarioRepository  extends JpaRepository <Bibliotecario, String>{
    
}
