package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "departamento")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Departamento {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamentoSequenceGenerator")
    @GenericGenerator(name = "departamentoSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "departamento_id_departamento_seq"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_departamento")
    private Long idDepartamento;

    @Column(name = "descricao", nullable = false, unique = true)
     @NotNull
    @NotEmpty
    private String descricao;

    @Column(name = "sigla", unique = true, length = 10)
    @NotNull
    @NotEmpty
    private String sigla;

}
