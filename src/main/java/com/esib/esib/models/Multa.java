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
@Table(name = "multa")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Multa {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "multaSequenceGenerator")
    @GenericGenerator(name = "multaSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "multa_id_multa_seq"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_multa")
    private Integer idMulta;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_emprestimo", nullable = false)
    private Emprestimo emprestimo;

    @Column(name = "valor_multa", nullable = false)
    private Double valorMulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

}
