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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "movimento")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Movimento {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimentoSequenceGenerator")
    @GenericGenerator(name = "movimentoSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "movimento_id_movimento_seq"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_movimento")
    private Integer idMovimento;

    @Column(name = "data_movimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataMovimento;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_tipo_movimento", nullable = false)
    private TipoMovimento tipoMovimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilizador")
    private Utilizador utilizador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bibliotecario", nullable = false)
    private Bibliotecario bibliotecario;

    @Column(name = "observacao", columnDefinition = "text")
    private String observacao;

}
