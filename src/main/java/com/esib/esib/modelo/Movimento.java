
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Movimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimento", nullable = false)
    private Long idMovimento;

    @Column(name = "data_movimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimento;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bibliotecario idBibliotecario;

    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Obra idObra;

    @JoinColumn(name = "id_tipo_movimento", referencedColumnName = "id_tipo_movimento", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoMovimento idTipoMovimento;

    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilizador idUtilizador;

}