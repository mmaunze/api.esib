package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "estudante")
@Data // Lombok annotation para gerar getters, setters e outros métodos
@EqualsAndHashCode(callSuper = false)
public class Estudante extends Utilizador {

    // Chave primária herdada de Utilizador (id_utilizador)
    
    @ManyToOne(fetch = FetchType.EAGER) // Carrega curso junto com estudante (opcional)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "nivel", nullable = false)
    private Integer nivel; // Ex: 1 (1º ano), 2 (2º ano), ...

    // Atributos adicionais

    @Column(name = "numero_matricula", length = 20)
    private String numeroMatricula;

    @Column(name = "telefone", length = 20)
    private String telefone;

    

}
