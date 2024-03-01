
package com.esib.esib.modelo;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        @UniqueConstraint(columnNames = { "sigla" }),
        @UniqueConstraint(columnNames = { "descricao" }) })
@XmlRootElement
@Data
public class Curso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(Curso.class.getName());

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
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
    @JoinColumn(name = "id_faculdade", referencedColumnName = "id_faculdade", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private Faculdade faculdade;

    /**
     *
     */
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private AreaCientifica areaCientifica;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "curso", fetch = LAZY)
    private List<Monografia> monografiaList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "curso", fetch = LAZY)
    private List<Estudante> estudanteList;


}