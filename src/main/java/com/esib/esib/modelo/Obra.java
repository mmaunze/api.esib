
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "localizacao" }) })
@XmlRootElement
@Data
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra", nullable = false)
    private Long idObra;

    @Basic(optional = false)
    @Column(name = "autor_1", nullable = false, length = 70)
    private String autor1;

    @Column(name = "autor_2", length = 70)
    private String autor2;

    @Column(name = "autor_3", length = 70)
    private String autor3;

    @Basic(optional = false)
    @Column(name = "titulo", nullable = false, length = 70)
    private String titulo;

    @Basic(optional = false)
    @Size(min=4, max=4)
    @Column(name = "ano_publicacao", nullable = false,  length = 4)
    private int anoPublicacao;

    @Basic(optional = false)
    @Column(name = "nr_paginas", nullable = false)
    private int nrPaginas;

    @Basic(optional = false)
    @Column(name = "local_publicacao", nullable = false, length = 70)
    private String localPublicacao;

    @Column(name = "assunto", length = 255)
    private String assunto;

    @Column(name = "localizacao", length = 200)
    private String localizacao;

    @Column(name = "fotografia", length = 90)
    private String fotografia;

    @Column(name = "referencia", length = 30)
    private String referencia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObra", fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimoList;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreaCientifica idArea;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado idEstado;
    @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Idioma idIdioma;
    @JoinColumn(name = "id_tipo_obra", referencedColumnName = "id_tipo_obra", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoObra idTipoObra;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "obra", fetch = FetchType.LAZY)
    private RevistaPeriodica revistaPeriodica;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "obra", fetch = FetchType.LAZY)
    private Livro livro;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "obra", fetch = FetchType.LAZY)
    private Monografia monografia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObra", fetch = FetchType.LAZY)
    private List<Reserva> reservaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObra", fetch = FetchType.LAZY)
    private List<Movimento> movimentoList;
}
