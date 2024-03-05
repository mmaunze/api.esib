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
public class Cta implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(Cta.class.getName());

    /**
     *
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id_utilizador", nullable = false)
    private Long id;

    /**
     *
     */
    @Column(length = 50)
    private String grau;

    /**
     *
     */
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Cargo cargo;

    /**
     *
     */
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Utilizador utilizador;

}
