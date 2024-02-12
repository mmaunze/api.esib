package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "monografia")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Herança de classe única
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Monografia extends Obra {

    @Id // Identifica a propriedade como chave primária (herdada de Obra)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID (substitui a estratégia da
                                                        // classe pai)
    @Column(name = "id_obra")
    private Integer idMonografia; // Renomeia a coluna para evitar conflito

    @Column(name = "id_curso", nullable = false)
    private Integer idCurso;

    @Column(name = "supervisor", nullable = false, length = 70)
    private String supervisor;

    @Column(name = "co_supervisor", length = 70)
    private String coSupervisor;

    @ManyToOne(fetch = FetchType.EAGER) // Carrega universidade junto com monografia (opcional)
    @JoinColumn(name = "id_faculdade") // Reutiliza id_faculdade como chave estrangeira
    private Faculdade faculdade;

    @Column(name = "data_publicacao")
    private java.util.Date dataPublicacao;

    @Column(name = "tiragem")
    private Integer tiragem;

    @Column(name = "isbn_13", length = 13)
    private String isbn13;

}
