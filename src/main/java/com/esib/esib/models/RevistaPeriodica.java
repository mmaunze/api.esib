package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "revista_periodica")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Herança de classe única
@EqualsAndHashCode(callSuper = false)
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class RevistaPeriodica extends Obra {

    @Id // Identifica a propriedade como chave primária (herdada de Obra)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID (substitui a estratégia da
                                                        // classe pai)
    @Column(name = "id_obra")
    private Long idRevistaPeriodica; // Renomeia a coluna para evitar conflito

    @Column(name = "nome", nullable = false, length = 255)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "issn", nullable = false)
    @NotNull
    @NotEmpty
    private Long issn;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "numero", nullable = false)
    @NotNull
    @NotEmpty
    private Long numero;

    @Column(name = "editora", nullable = false, length = 70)
    @NotNull
    @NotEmpty
    private String editora;

    // Atributos adicionais que você pode considerar:

    @Column(name = "data_publicacao")
    @NotNull
    @NotEmpty
    private java.util.Date dataPublicacao;

    @Column(name = "periodicidade", length = 50) // Ex: Semanal, Mensal, Anual
    private String periodicidade;

    @Column(name = "url_revista")
    private String urlRevista; 

}
