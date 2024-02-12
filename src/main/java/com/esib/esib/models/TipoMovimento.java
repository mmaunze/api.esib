package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "tipo_movimento")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class TipoMovimento {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoMovimentoSequenceGenerator")
    @GenericGenerator(name = "tipoMovimentoSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tipo_movimento_id_tipo_movimento_seq"),
        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_tipo_movimento")
    private Integer idTipoMovimento;

    @Column(name = "descricao", nullable = false, unique = true, length = 100)
    private String descricao;

}
