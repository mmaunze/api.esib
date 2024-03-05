package com.esib.esib.model;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.*;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Entity
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sigla"}),
    @UniqueConstraint(columnNames = {"descricao"})})
@XmlRootElement
@Data

public class Faculdade implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(Faculdade.class.getName());

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_faculdade", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String sigla;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "faculdade", fetch = LAZY)
    private List<Curso> cursoList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "faculdade", fetch = LAZY)
    private List<Monografia> monografiaList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "faculdade", fetch = LAZY)
    private List<Bibliotecario> bibliotecarioList;

}
