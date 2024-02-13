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
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@Data
@XmlRootElement

public class Bibliotecario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bibliotecario", nullable = false)
    private Long idBibliotecario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBibliotecario", fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBibliotecario", fetch = FetchType.LAZY)
    private List<PagamentoMulta> pagamentoMultaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBibliotecario", fetch = FetchType.LAZY)
    private List<Devolucao> devolucaoList;

    @JoinColumn(name = "id_faculdade", referencedColumnName = "id_faculdade", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Faculdade idFaculdade;

    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_utilizador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Utilizador utilizador;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBibliotecario", fetch = FetchType.LAZY)
    private List<Movimento> movimentoList;

}
