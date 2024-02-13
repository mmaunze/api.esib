
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

public class Faculdade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_faculdade", nullable = false)
    private Long idFaculdade;

    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String sigla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFaculdade", fetch = FetchType.LAZY)
    private List<Curso> cursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFaculdade", fetch = FetchType.LAZY)
    private List<Monografia> monografiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFaculdade", fetch = FetchType.LAZY)
    private List<Bibliotecario> bibliotecarioList;
}