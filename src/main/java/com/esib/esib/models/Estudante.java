package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull
    @NotEmpty
    @Size(min=1, max =1)
    private Long nivel; // Ex: 1 (1º ano), 2 (2º ano), ...

    // Atributos adicionais

    @Column(name = "numero_matricula", length = 20)
    @NotNull
    @NotEmpty
    private String numeroMatricula;

    @Column(name = "telefone", length = 20)
    @NotNull
    @NotEmpty
    private String telefone;

}
