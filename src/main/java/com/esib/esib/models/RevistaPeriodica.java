package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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
    private Integer idRevistaPeriodica; // Renomeia a coluna para evitar conflito

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "issn", nullable = false)
    private Integer issn;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "editora", nullable = false, length = 70)
    private String editora;

    // Atributos adicionais que você pode considerar:

    @Column(name = "data_publicacao")
    private java.util.Date dataPublicacao;

    @Column(name = "periodicidade", length = 50) // Ex: Semanal, Mensal, Anual
    private String periodicidade;

    @Column(name = "area_tematica", length = 100)
    private String areaTematica;

    @Column(name = "url_revista")
    private String urlRevista; // Link para a revista online (opcional)
/* 
    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, carregue autores junto com a revista
    @JoinColumn(name = "id_autor") // Adicione uma coluna para autores (se necessário)
    private Autor autor; // Referência à entidade Autor
*/
    // ... outros atributos específicos

}
