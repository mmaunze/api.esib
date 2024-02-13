
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emprestimo", nullable = false)
    private Long idEmprestimo;
    @Basic(optional = false)
    @Column(name = "data_emprestimo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmprestimo;
    @Basic(optional = false)
    @Column(name = "data_para_devolucao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataParaDevolucao;
    @Basic(optional = false)
    @Column(nullable = false)
    private int atraso;
    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bibliotecario idBibliotecario;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado idEstado;
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Obra idObra;
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilizador idUtilizador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmprestimo", fetch = FetchType.LAZY)
    private List<Multa> multaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmprestimo", fetch = FetchType.LAZY)
    private List<Devolucao> devolucaoList;

}
