
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.*;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.*;
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
public class Movimento implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(Movimento.class.getName());

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimento", nullable = false)
    private Long id;

    /**
     *
     */
    @Column(name = "data_movimento", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataMovimento;

    /**
     *
     */
    @Column(name = "observacao", length = 255)
    private String observacao;

    /**
     *
     */
    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private Bibliotecario bibliotecario;

    /**
     *
     */
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private Obra obra;

    /**
     *
     */
    @JoinColumn(name = "id_tipo_movimento", referencedColumnName = "id_tipo_movimento", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { PERSIST, MERGE})
    private TipoMovimento tipoMovimento;

    /**
     *
     */
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador")
    @ManyToOne(fetch = LAZY)
    private Utilizador utilizador;


}