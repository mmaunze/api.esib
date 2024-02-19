
package com.esib.esib.modelo;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.TemporalType.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Movimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimento", nullable = false)
    private Long id;

    @Column(name = "data_movimento", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataMovimento;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Bibliotecario bibliotecario;

    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Obra obra;

    @JoinColumn(name = "id_tipo_movimento", referencedColumnName = "id_tipo_movimento", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private TipoMovimento tipoMovimento;

    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador")
    @ManyToOne(fetch = LAZY)
    private Utilizador utilizador;

}