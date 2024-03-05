package com.esib.esib.model;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Entity
@Table(name = "revista_periodica", catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Revista implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(Revista.class.getName());

    /**
     *
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int issn;

    /**
     *
     */
    private Long volume;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int numero;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String editora;

    /**
     *
     */
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Obra obra;

}
