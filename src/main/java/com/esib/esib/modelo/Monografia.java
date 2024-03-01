
package com.esib.esib.modelo;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Monografia implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(Monografia.class.getName());

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
    @Column(nullable = false, length = 70)
    private String supervisor;

    /**
     *
     */
    @Column(name = "co_supervisor", length = 70)
    private String coSupervisor;

    /**
     *
     */
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private Curso curso;

    /**
     *
     */
    @JoinColumn(name = "id_faculdade", referencedColumnName = "id_faculdade", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private Faculdade faculdade;

    /**
     *
     */
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private Obra obra;


}
