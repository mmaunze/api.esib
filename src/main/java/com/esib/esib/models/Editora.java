package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "editora")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Editora {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editoraSequenceGenerator")
    @GenericGenerator(name = "editoraSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "editora_id_editora_seq"),
        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_editora")
    private Integer idEditora;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "morada", length = 255)
    private String morada;

    @Column(name = "email", length = 100)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "editoraRef") // Carrega editora junto com livro (opcional)
    private Livro livro; // Referência à entidade Livro

}
