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
    private Integer idLivro; // Renomeia a coluna para evitar conflito

    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "edicao", nullable = false)
    private Integer edicao;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Column(name = "editora", nullable = false, length = 70)
    private String editora;

    @OneToOne(fetch = FetchType.EAGER) // Carrega editora junto com livro (opcional)
    @JoinColumn(name = "id_obra") // Reutiliza id_obra como chave estrangeira
    private Editora editoraRef; // Referência à entidade Editora

    // Atributos adicionais relacionados à edição

    @Column(name = "data_publicacao")
    private java.util.Date dataPublicacao;

    @Column(name = "tiragem")
    private Integer tiragem;

    @Column(name = "isbn_13", length = 13)
    private String isbn13;

}
