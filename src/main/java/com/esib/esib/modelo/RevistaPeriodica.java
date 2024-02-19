
package com.esib.esib.modelo;

import static javax.persistence.FetchType.*;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(name = "revista_periodica", catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class RevistaPeriodica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra", nullable = false)
    private Long idObra;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false)
    private int issn;
    private Long volume;
    @Basic(optional = false)
    @Column(nullable = false)
    private int numero;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String editora;
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Obra obra;

}