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
@Table(name = "tipo_utilizador")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class TipoUtilizador {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoUtilizadorSequenceGenerator")
    @GenericGenerator(name = "tipoUtilizadorSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tipo_utilizador_id_tipo_utilizador_seq"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_tipo_utilizador")
    private Long idTipoUtilizador;

    @Column(name = "descricao", unique = true, length = 100)
    @NotNull
    @NotEmpty
    private String descricao;

}
