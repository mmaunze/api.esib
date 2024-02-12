package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "bibliotecario")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Herança de classe única
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Bibliotecario extends Utilizador {

    // Chave primária herdada de Utilizador (id_utilizador)

    @ManyToOne(fetch = FetchType.EAGER) // Carrega faculdade junto com bibliotecário (opcional)
    @JoinColumn(name = "id_faculdade", nullable = false)
    private Faculdade faculdade;

    // Atributos específicos de um Bibliotecario

    @Column(name = "cargo", length = 50)
    @NotNull
    @NotEmpty
    private String cargo;

    @Column(name = "numero_registro", length = 20)
    @NotNull
    @NotEmpty
    private String numeroRegistro;

}
