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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "emprestimo")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Emprestimo {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimoSequenceGenerator")
    @GenericGenerator(name = "emprestimoSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "emprestimo_id_emprestimo_seq"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_emprestimo")
    private Long idEmprestimo;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilizador", nullable = false)
    @NotNull
    @NotEmpty
    private Utilizador utilizador;

    @Column(name = "data_para_devolucao", nullable = false)
    @NotNull
    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataParaDevolucao;

    @Column(name = "atraso", nullable = false)
    private Long atraso;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @NotEmpty
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @NotEmpty
    
    @JoinColumn(name = "id_bibliotecario", nullable = false)
    @NotNull
    @NotEmpty
    private Bibliotecario bibliotecario;

    @Column(name = "data_emprestimo")
    @NotNull
    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataEmprestimo;

}
