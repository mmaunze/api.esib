
package com.esib.esib.modelo;

import static javax.persistence.FetchType.*;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Estudante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_utilizador", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private int nivel;

    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Curso curso;

    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Utilizador utilizador;

}
