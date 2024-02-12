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
@Table(name = "cta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Herança de classe única
@EqualsAndHashCode(callSuper = false)
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Cta extends Utilizador {

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @Column(name = "grau", length = 50)
    @NotNull
    @NotEmpty
    private String grau;

}
