
package com.esib.esib.modelo;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "sigla" }),
        @UniqueConstraint(columnNames = { "descricao" }) })
@XmlRootElement
@Data
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String descricao;

    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String sigla;

    @JoinColumn(name = "id_faculdade", referencedColumnName = "id_faculdade", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Faculdade faculdade;

    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private AreaCientifica areaCientifica;

    @OneToMany(cascade = ALL, mappedBy = "curso", fetch = LAZY)
    private List<Monografia> monografiaList;

    @OneToMany(cascade = ALL, mappedBy = "curso", fetch = LAZY)
    private List<Estudante> estudanteList;

}