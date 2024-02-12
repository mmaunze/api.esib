package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "curso")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Curso {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cursoSequenceGenerator")
    @GenericGenerator(name = "cursoSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "curso_id_curso_seq"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "descricao", nullable = false, unique = true)
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_faculdade", nullable = false)
    private Faculdade faculdade;

    @Column(name = "sigla", nullable = false, unique = true, length = 10)
    private String sigla;

}
