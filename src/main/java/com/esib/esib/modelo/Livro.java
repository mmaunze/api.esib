
package com.esib.esib.modelo;

import static javax.persistence.FetchType.*;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "isbn" }) })
@XmlRootElement
@Data
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String isbn;

    @Basic(optional = false)
    @Column(nullable = false)
    private int edicao;

    @Basic(optional = false)
    @Column(nullable = false)
    private int volume;

    @Basic(optional = false)
    @Column(nullable = false, length = 70)
    private String editora;

    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Obra obra;

}
