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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "livro")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Herança de classe única
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Livro extends Obra {

    @Id // Identifica a propriedade como chave primária (herdada de Obra)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID (substitui a estratégia da
                                                        // classe pai)
    @Column(name = "id_obra")
    private Long idLivro; // Renomeia a coluna para evitar conflito

    @Column(name = "edicao", nullable = false)
    private Long edicao;

    @Column(name = "volume", nullable = false)

    private Long volume;

    @Column(name = "editora", nullable = false, length = 70)
    @NotNull
    @NotEmpty
    private String editora;

    @OneToOne(fetch = FetchType.EAGER) // Carrega editora junto com livro (opcional)
    @JoinColumn(name = "id_obra") // Reutiliza id_obra como chave estrangeira
    private Editora editoraRef; // Referência à entidade Editora

    // Atributos adicionais relacionados à edição

    @Column(name = "data_publicacao")
    private java.util.Date dataPublicacao;

    @Column(name = "isbn", length = 13)
    @NotNull
    @NotEmpty
    @Size(min=13, max=13)
    private String isbn;

}
